package com.example.JokeBot.service;

import com.example.JokeBot.config.BotConfig;
import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class TelegramBot extends TelegramLongPollingBot {
    static final String ERROR_TEXT = "Error occurred: ";


    final BotConfig config;

    public TelegramBot(BotConfig config) {
        this.config = config;
    }

    @Override
    public String getBotUsername() {

        return config.getBotUserName();
    }


    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()){
            String messageText = update.getMessage().getText();
            long cahatId = update.getMessage().getChatId();
            switch (messageText){
                case "/start" -> showStart(cahatId,update.getMessage().getText());
            }
        }

    }
    private void showStart(long chatId, String name){
        String answer = EmojiParser.parseToUnicode(
                "Hi, "+name+"! :smile:"+" Nice to meet you"
        );
        sendMessage(chatId, answer);
    }
    private void sendMessage(long chatId, String textToSend){
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);


        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("weather");
        row.add("get random joke");
        keyboardRows.add(row);
        row = new KeyboardRow();
        row.add("register");
        row.add("check my data");
        row.add("delete my data");
        keyboardRows.add(row);
        keyboardMarkup.setKeyboard(keyboardRows);
        message.setReplyMarkup(keyboardMarkup);



        executeMessage(message);
    }
    private void executeMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {

        }
    }
}
