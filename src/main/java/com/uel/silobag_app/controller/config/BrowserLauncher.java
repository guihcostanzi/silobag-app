package com.uel.silobag_app.controller.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BrowserLauncher {

    @EventListener(ApplicationReadyEvent.class)
    public void openBrowserAfterStartup() {
        String url = "http://localhost:8080/inicio";
        System.out.println("Aplicação pronta! Tentando abrir o navegador em: " + url);

        try {
            Thread.sleep(3000);

            String os = System.getProperty("os.name").toLowerCase();

            ProcessBuilder processBuilder = new ProcessBuilder();
            if (os.contains("win")) {
                // Para Windows:
                processBuilder.command("rundll32", "url.dll,FileProtocolHandler", url);
            } else if (os.contains("mac")) {
                // Para macOS
                processBuilder.command("open", url);
            } else if (os.contains("nix") || os.contains("nux")) {
                // Para Linux
                processBuilder.command("xdg-open", url);
            } else {
                System.err.println("Não foi possível detectar o sistema operacional para abrir o navegador.");
                System.out.println("Por favor, acesse manualmente: " + url);
                return;
            }
            
            // Inicia o processo
            processBuilder.start();
            System.out.println("Comando de abertura do navegador enviado ao sistema operacional via ProcessBuilder.");

        } catch (Exception e) {
            System.err.println("Ocorreu um erro ao tentar abrir o navegador automaticamente.");
            e.printStackTrace();
            System.out.println("Por favor, acesse manualmente: " + url);
        }
    }
}