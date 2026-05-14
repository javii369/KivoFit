<?php

namespace App\Http\Controllers;

use App\Models\ConsultaIa;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Http;

class ConsultaIaController extends Controller
{
    private const SYSTEM_PROMPT = <<<'PROMPT'
Eres KivoBot, el asistente personal de fitness del gimnasio KivoFit.
Respondes en español de manera amigable, concisa y motivadora.
Ayudas con: nutrición, rutinas de ejercicio, técnica, recuperación muscular y bienestar general.
No das consejos médicos ni diagnósticos. Si el usuario tiene una lesión o problema de salud, recomiéndale consultar a un profesional.
Mantén las respuestas breves y directas (máximo 3-4 párrafos). Usa listas cuando sea útil.
PROMPT;

    public function index(Request $request)
    {
        $consultas = ConsultaIa::where('user_id', $request->user()->id)
            ->with('dieta', 'rutina')
            ->orderBy('fecha', 'desc')
            ->get();

        return response()->json($consultas);
    }

    public function store(Request $request)
    {
        $request->validate([
            'consulta_usuario' => 'required|string|max:2000',
            'dieta_id'         => 'nullable|exists:dietas,id',
            'rutina_id'        => 'nullable|exists:rutinas,id',
        ]);

        $respuesta = $this->callAnthropic($request->consulta_usuario);

        $consulta = ConsultaIa::create([
            'user_id'          => $request->user()->id,
            'consulta_usuario' => $request->consulta_usuario,
            'respuesta_ia'     => $respuesta,
            'dieta_id'         => $request->dieta_id,
            'rutina_id'        => $request->rutina_id,
        ]);

        return response()->json([
            'message'  => 'Consulta guardada correctamente',
            'consulta' => $consulta,
        ], 201);
    }

    private function callAnthropic(string $userMessage): string
    {
        $apiKey = env('ANTHROPIC_API_KEY');

        if (empty($apiKey)) {
            return '⚠️ El asistente IA no está configurado. Contacta con el administrador.';
        }

        try {
            $response = Http::withHeaders([
                'x-api-key'         => $apiKey,
                'anthropic-version' => '2023-06-01',
                'content-type'      => 'application/json',
            ])->timeout(30)->post('https://api.anthropic.com/v1/messages', [
                'model'      => 'claude-haiku-4-5-20251001',
                'max_tokens' => 600,
                'system'     => self::SYSTEM_PROMPT,
                'messages'   => [
                    ['role' => 'user', 'content' => $userMessage],
                ],
            ]);

            if ($response->successful()) {
                return $response->json('content.0.text')
                    ?? 'No pude generar una respuesta. Inténtalo de nuevo.';
            }

            return 'El asistente no está disponible en este momento. Inténtalo más tarde.';
        } catch (\Exception) {
            return 'Error al conectar con el asistente. Comprueba tu conexión e inténtalo de nuevo.';
        }
    }
}
