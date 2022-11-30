package as.florenko.weatherbottg.service;

import as.florenko.weatherbottg.Keyboard;
import as.florenko.weatherbottg.Request;
import as.florenko.weatherbottg.config.BotConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;

@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {

    private Request request = new Request();
    @Autowired
    final BotConfig config;

    public TelegramBot(BotConfig config) {
        this.config = config;
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (message) {
                case "/start":
                    startBot(chatId, update.getMessage().getChat().getFirstName());
                    break;

                case "/g":


                    break;
                default:
                    sendMessage(chatId, "я это еще не умею");
            }
        }
        if (update.hasCallbackQuery()) {
            String callBackMessage = update.getCallbackQuery().getData();
            long callBackChatId = update.getCallbackQuery().getMessage().getChatId();
            switch (callBackMessage) {
                case "Samara":
                    sendStartKeyboard(callBackChatId, request.getWeather(request.samLat, request.samLon).toString(), new Keyboard().sendStartButton());
                    break;
                case "Nb":
                    sendStartKeyboard(callBackChatId, request.getWeather(request.nbLat, request.nbLon).toString(), new Keyboard().sendStartButton());
                    break;
                case "T":
                    sendPhoto(callBackChatId);
                    break;
                default:
                    sendMessage(callBackChatId, "Что-то пошло не так(");
            }
        }
    }

    private void startBot(long chatId, String name) {
        String answer = "Добро пожаловать, " + name + "\nВыберите населенный пункт \nдля получения данных о погоде";
        sendStartKeyboard(chatId, answer, new Keyboard().sendStartButton());
    }

    private void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);

        try {
            execute(message);

        } catch (TelegramApiException e) {
            System.out.println(e.getMessage());
        }
    }

    private void sendStartKeyboard(long chatId, String text, InlineKeyboardMarkup inlineKeyboardMarkup) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);
        message.setReplyMarkup(inlineKeyboardMarkup);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendPhoto(long chatId) {
        SendPhoto sendPhoto = new SendPhoto();
        File file = new File("D:\\Java Project\\WeatherBotTG\\q.jpg");

        InputFile inputFile = new InputFile(file);
        sendPhoto.setChatId(String.valueOf(chatId));

        try {
            sendPhoto.setPhoto(inputFile);
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
