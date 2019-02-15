package ch.wetwer.propagandabot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class AnnieBot extends TelegramLongPollingBot {


    public void onUpdatesReceived(List<Update> updates) {
        for (Update up :
                updates) {
            onUpdateReceived(up);
        }
    }

    public void onUpdateReceived(Update update) {
        try {

            String text = update.getMessage().getText();

            switch (text.split("@")[0]) {
                case "/gang":
                    sendSong(update, Songs.GUCCI_GANG);
                    break;
                case "/bitch":
                    sendSong(update, Songs.BITCH_LASAGNA);
                    break;
                case "/basken":
                    sendSong(update, Songs.BASKEN_2017);
                    break;
                case "/victoria":
                    sendSong(update, Songs.PRIMA_VICTORIA);
                    break;
                case "/team":
                    sendSong(update, Songs.OHNE_MEIN_TEAM);
                    break;
                case "/jungle":
                    sendSong(update, Songs.RUN_THROUGH_THE_JUNGLE);
                    break;
                case "/bellaciao":
                    sendSong(update, Songs.BELLA_CIAO);
                    break;
                case "/wouldnever":
                    sendSong(update, Songs.NEVER_GIVE_YOU_UP);
                    break;
                case "/alah":
                    sendSong(update, Songs.SALIL_SAVARIM);
                    break;
                case "/hitormiss":
                    sendSong(update, Songs.HIT_OR_MISS);
                    break;
                case "/button":
                    inlineButton(update);
                    break;
                case "/help":
                    SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                            .setChatId(update.getMessage().getChatId())
                            .setText("SONGS:\n" +
                                    "/bitch\t- Bitch Lasagna\n" +
                                    "/basken\t- Basken 2017\n" +
                                    "/gang\t- Gucci Gang\n" +
                                    "/jungle\t- Run Through the Jungle\n" +
                                    "/bellaciao\t- Bella Ciao (Hugel Remix)\n" +
                                    "/victoria\t- Prima Victoria\n" +
                                    "/hitormiss\t- Hit or Miss\n" +
                                    "/alah\t- Salil Sawarim\n" +
                                    "/wouldnever\t- Never Gonna Give You Up\n" +
                                    "/team\t- Ohne mein Team");
                    execute(message); // Call method to send the message

                    break;
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void inlineButton(Update update) throws TelegramApiException {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setUrl("http://scorewinner.ch:8081");
        button.setText("Movie Score");

        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();

        row.add(button);
        row.add(button);
        row.add(button);
        rows.add(row);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rows);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("blub");

        sendMessage
                .setReplyMarkup(inlineKeyboardMarkup)
                .setChatId(update.getMessage().getChatId());

        execute(sendMessage);
    }

    private void sendSong(Update update, String[] song) throws TelegramApiException {
        for (String s : song) {
            SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                    .setChatId(update.getMessage().getChatId())
                    .setText(s);
            execute(message); // Call method to send the message
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String getBotUsername() {
        return "AnFrankBot";
    }

    @Override
    public String getBotToken() {
        return "token";
    }
}
