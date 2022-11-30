package as.florenko.weatherbottg;

import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class Keyboard {

    public InlineKeyboardMarkup sendStartButton() {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton_Samara = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton_NB = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton_TR = new InlineKeyboardButton();

        inlineKeyboardButton_Samara.setText("Самара");
        inlineKeyboardButton_Samara.setCallbackData("Samara");

        inlineKeyboardButton_NB.setText("Новый буян");
        inlineKeyboardButton_NB.setCallbackData("Nb");

        inlineKeyboardButton_TR.setText("Т");
        inlineKeyboardButton_TR.setCallbackData("T");

        List<InlineKeyboardButton> list = new ArrayList<>();
        list.add(inlineKeyboardButton_Samara);
        list.add(inlineKeyboardButton_NB);
        list.add(inlineKeyboardButton_TR);

        List<List<InlineKeyboardButton>> finalList = new ArrayList<>();
        finalList.add(list);
        inlineKeyboardMarkup.setKeyboard(finalList);
        return inlineKeyboardMarkup;
    }

}
