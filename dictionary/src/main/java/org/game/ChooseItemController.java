package org.game;
import com.example.game.game.ChooseItem;
import com.example.game.game.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.FileNotFoundException;

public class ChooseItemController extends Controller{


    @FXML
    private Label correctLabel1;

    @FXML
    private ImageView imageView1;

    @FXML
    private ImageView imageView2;

    @FXML
    private ImageView imageView3;

    @FXML
    private ImageView imageView4;

    @FXML
    private Label questionBox;

    @FXML
    private Label scoreBox;

    @FXML
    private Label scoreBox1;
    private ChooseItem chooseItem;
    private Item correctItem;
    private Item[] items;
    @FXML
    private Label correctLabel = new Label();
    private int numberOfQuestions = 0;
    public ChooseItemController() throws FileNotFoundException {
        items = new Item[4];
        chooseItem = ChooseItem.getChooseItem();
    }

    public void correct() {
        correctLabel.setText("Correct!");
        chooseItem.increaseHighscore();
        scoreBox.setText("Score: " + chooseItem.getScore());
    }

    private boolean checkIfArrayIsNotFull(Item[] items) {
        for (int i = 0; i < 4; i++) {
            if (items[i] == null) {
                return true;
            }
        }
        return false;
    }

    public void initializeQuestion(ActionEvent event) {
        // Thiết lập nhãn correctLabel để yêu cầu người chơi chọn mục đúng
        correctLabel.setText("Choose the correct item!");

        // Chọn một mục ngẫu nhiên từ danh sách các mục và đánh dấu mục này là được chọn
        correctItem = chooseItem.returnRandomItem();
        correctItem.setChosen(true);

        // Chọn một vị trí ngẫu nhiên trong mảng items để đặt mục đúng vào
        int randomIndex = (int) (Math.random() * 4);
        items[randomIndex] = correctItem;

        // Lặp lại cho đến khi mảng items đã được điền đầy
        while (checkIfArrayIsNotFull(items)) {
            // Chọn một vị trí ngẫu nhiên trong mảng items
            randomIndex = (int) (Math.random() * 4);
            // Chọn một mục ngẫu nhiên từ danh sách các mục
            Item temp = chooseItem.returnRandomItem();
            // Kiểm tra xem vị trí đã được điền và mục đã được chọn chưa
            if (items[randomIndex] == null && (!temp.isChosen())) {
                // Nếu vị trí trống và mục chưa được chọn, đặt mục vào vị trí và đánh dấu mục này là đã chọn
                items[randomIndex] = temp;
                temp.setChosen(true);
            }
        }

        // Đặt hình ảnh của các mục vào các ImageView tương ứng
        imageView1.setImage(items[0].getImage());
        imageView2.setImage(items[1].getImage());
        imageView3.setImage(items[2].getImage());
        imageView4.setImage(items[3].getImage());

        // Đặt câu hỏi của mục đúng vào questionBox để hiển thị cho người chơi
        questionBox.setText(correctItem.getQuestion());
    }

    @FXML
    void clickImageView1(MouseEvent event) {

    }

    @FXML
    void clickImageView2(MouseEvent event) {

    }

    @FXML
    void clickImageView3(MouseEvent event) {

    }

    @FXML
    void clickImageView4(MouseEvent event) {

    }

    @FXML
    void end(ActionEvent event) {

    }

    @FXML
    void setQuestionByAction(ActionEvent event) {

    }

    @FXML
    void switchToGameScene(ActionEvent event) {

    }

}
