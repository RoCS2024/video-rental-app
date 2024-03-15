package com.video.rental.app.controllers.dashboard;

import com.video.rental.app.controllers.item.ItemController;
import com.video.rental.app.exceptions.ItemNotFoundException;
import com.video.rental.app.facade.item.ItemFacade;
import com.video.rental.app.facade.item.impl.ItemFacadeImpl;
import com.video.rental.app.model.item.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.List;

public class DashboardController {

    private ItemFacade itemFacade = new ItemFacadeImpl();

    @FXML
    private Button dashboardButton;

    @FXML
    TableView tableView;

    @FXML
    protected void buttonPressed() {


        tableView.getItems().clear();
        List<Item> items = itemFacade.getAllItems();

        ObservableList<Item> data = FXCollections.observableArrayList(items);
        tableView.setItems(data);

        TableColumn itemIdColumn = new TableColumn("ITEM ID");
        itemIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        itemIdColumn.getStyleClass().addAll("item-id-column");
        TableColumn titleColumn = new TableColumn("TITLE");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleColumn.getStyleClass().addAll("title-column");
        TableColumn genreColumn = new TableColumn("GENRE");
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        genreColumn.getStyleClass().addAll("genre-column");
        TableColumn copiesColumn = new TableColumn("COPIES");
        copiesColumn.getStyleClass().addAll("copies-column");
        copiesColumn.setCellValueFactory(new PropertyValueFactory<>("copies"));
        TableColumn actionColumn = new TableColumn("ACTION");
        actionColumn.setCellValueFactory(new PropertyValueFactory<>(""));
        actionColumn.getStyleClass().addAll("item-action-column");

        Callback<TableColumn<Item, String>, TableCell<Item, String>> cellFactory
                = //
                new Callback<TableColumn<Item, String>, TableCell<Item, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Item, String> param) {
                        final TableCell<Item, String> cell = new TableCell<Item, String>() {
                            final Button editButton = new Button();
                            final Button deleteButton = new Button();
                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    editButton.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/assets/edit-item.png"))));
                                    deleteButton.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/assets/delete-item.png"))));
                                    editButton.setOnAction(event -> {
                                        Item editItem = getTableView().getItems().get(getIndex());
                                        showItemView(editItem);
                                    });
                                    deleteButton.setOnAction(event -> {
                                        Item deleteItem = getTableView().getItems().get(getIndex());
                                        try {
                                            itemFacade.deleteItemById(deleteItem.getId());
                                            data.remove(deleteItem);
                                            tableView.refresh();
                                        } catch (ItemNotFoundException ex) {
                                            ex.printStackTrace();
                                        }

                                    });
                                    HBox hbox = new HBox(editButton, deleteButton);
                                    hbox.setSpacing(10);
                                    hbox.setAlignment(Pos.BASELINE_CENTER);
                                    setGraphic(hbox);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };
        actionColumn.setCellFactory(cellFactory);
        tableView.getColumns().addAll(itemIdColumn, titleColumn, genreColumn, copiesColumn, actionColumn);

    }


    private void showItemView(Item item) {
        try {
            Stage itemView = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/ItemView.fxml"));
            VBox itemViewLayout = new VBox();
            itemViewLayout = loader.load();
            ItemController itemController = loader.getController();
            itemController.setItem(item);
            Scene scene = new Scene(itemViewLayout);
            itemView.setScene(scene);
            itemView.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }






}
