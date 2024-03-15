package com.video.rental.app.controllers.item;

import com.video.rental.app.controllers.dashboard.DashboardController;
import com.video.rental.app.exceptions.ItemNotFoundException;
import com.video.rental.app.facade.item.ItemFacade;
import com.video.rental.app.facade.item.impl.ItemFacadeImpl;
import com.video.rental.app.model.item.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ItemController {

    @FXML
    private TextField itemId;

    @FXML
    private TextArea itemTitle;

    @FXML
    private TextField itemGenre;

    @FXML
    private TextField itemCopies;

    @FXML
    private Button itemUpdateButton;

    private Item item;

    private ItemFacade itemFacade = new ItemFacadeImpl();

    @FXML
    protected void onAddOrUpdateClicked(ActionEvent event) {
        Item updateItem = new Item();
        updateItem.setId(itemId.getText());
        updateItem.setTitle(itemTitle.getText());
        updateItem.setGenre(itemGenre.getText());
        updateItem.setCopies(Integer.parseInt(itemCopies.getText()));
        try {
            itemFacade.updateItem(updateItem);
        } catch(ItemNotFoundException ex) {
            ex.printStackTrace();;
        }
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void setItem(Item item) {
        this.item = item;
        itemId.setText(item.getId());
        itemTitle.setText(item.getTitle());
        itemGenre.setText(item.getGenre());
        itemCopies.setText(String.valueOf(item.getCopies()));
        itemUpdateButton.setText("UPDATE");
    }


}
