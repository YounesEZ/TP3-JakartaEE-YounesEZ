package ma.emsi.tp3ezbidayounes;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.SystemMessage;
import jakarta.enterprise.context.Dependent;

import java.io.Serializable;
import java.rmi.RemoteException;

@Dependent
public class LlmClient implements Serializable {

    public interface Assistant {
        @SystemMessage("""
                Un guide touristique qui recommande deux endroits où aller selon le pays ou la ville désigné par l'utilisateur
                La réponse doit etre au format JSON comme ceci :
                {
                  "ville_ou_pays": "nom de la ville ou du pays",
                  "endroits_a_visiter": ["endroit 1", "endroit 2"],
                  "prix_moyen_repas": "<prix> <devise du pays>"
                }
                Restriction : Ne pas donnez un autre format de réponse différent; 
                """)
        String chat(String prompt);
    }

    private Assistant assistant;
    private ChatMemory chatMemory;

    public LlmClient() {

        ChatLanguageModel modele = GoogleAiGeminiChatModel.builder()
                .temperature(0.7)
                .modelName("gemini-1.5-flash")
                .apiKey(System.getenv("GEMINI_KEY"))
                .build();

        this.chatMemory = MessageWindowChatMemory.withMaxMessages(10);
        this.assistant = AiServices.builder(Assistant.class)
                .chatLanguageModel(modele)
                .chatMemory(chatMemory)
                .build();
    }


    public String envoyerMessage(String question) {
        return this.assistant.chat(question);
    }
}