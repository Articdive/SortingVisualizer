package de.articdive.sortingvisualizer;

import de.articdive.sortingvisualizer.sortings.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

/**
 * @author Articdive
 */
public final class SortingVisualizer extends Application {
    public int ARRAY_SIZE = 90;
    public int SPECTRUM_SIZE = 200000000;
    private SortingView sortingView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sorting Visualizer");
        primaryStage.setHeight(600);
        primaryStage.setWidth(1200);
        primaryStage.setResizable(false);

        VBox pane = new VBox(getMenuBar());
        pane.setBackground(Background.EMPTY);
        pane.setAlignment(Pos.BASELINE_CENTER);

        {
            sortingView = new SortingView(this);
            sortingView.setHeight(300);
            sortingView.setWidth(1040);

            pane.getChildren().add(sortingView);
        }

        // Button that sorts the data
        {
            Button sortButton = new Button("Sort/Stop");
            sortButton.setPadding(new Insets(30, 0, 30, 0));
            sortButton.setPrefWidth(520);
            sortButton.setFont(Font.font("Valken", 20.0));
            sortButton.setStyle("-fx-background-color: #3282b8");
            sortButton.setOnAction(event -> {
                if (sortingView.isSorting()) {
                    sortingView.endSorting();
                } else {
                    sortingView.beginSorting();
                }
            });
            pane.getChildren().add(sortButton);
        }

        Scene scene = new Scene(pane, 640, 480);
        scene.setFill(Color.rgb(27, 38, 44));

        primaryStage.setScene(scene);
        primaryStage.show();

        sortingView.generateData();
    }

    @NotNull
    private MenuBar getMenuBar() {
        MenuBar menuBar = new MenuBar();

        // System
        {
            Menu systemMenu = new Menu("Sorter");

            // Generate Data
            {
                MenuItem generateDataItem = new MenuItem("Generate Data");
                generateDataItem.setOnAction(event -> sortingView.generateData());
                systemMenu.getItems().add(generateDataItem);
            }

            // Clear
            {
                MenuItem clearItem = new MenuItem("Clear");
                clearItem.setOnAction(event -> stopSorting());

                systemMenu.getItems().add(clearItem);
            }

            // Set Algorithm
            {
                Menu algorithmMenu = new Menu("Algorithms");

                // BubbleSort
                {
                    MenuItem bubbleSortItem = new MenuItem("Bubble Sort");
                    bubbleSortItem.setOnAction(event -> sortingView.setSortingAlgorithm(new BubbleSort(sortingView)));

                    algorithmMenu.getItems().add(bubbleSortItem);
                }

                // BogoSort
                {
                    MenuItem bogoSortItem = new MenuItem("Bogo Sort");
                    bogoSortItem.setOnAction(event -> sortingView.setSortingAlgorithm(new BogoSort(sortingView)));

                    algorithmMenu.getItems().add(bogoSortItem);
                }

                // CocktailShakerSort
                {
                    MenuItem bogoSortItem = new MenuItem("Cocktailshaker Sort");
                    bogoSortItem.setOnAction(event -> sortingView.setSortingAlgorithm(new CocktailShakerSort(sortingView)));

                    algorithmMenu.getItems().add(bogoSortItem);
                }

                // CombSort
                {
                    MenuItem bogoSortItem = new MenuItem("Comb Sort");
                    bogoSortItem.setOnAction(event -> sortingView.setSortingAlgorithm(new CombSort(sortingView)));

                    algorithmMenu.getItems().add(bogoSortItem);
                }

                // InsertionSort
                {
                    MenuItem bogoSortItem = new MenuItem("Insertion Sort");
                    bogoSortItem.setOnAction(event -> sortingView.setSortingAlgorithm(new InsertionSort(sortingView)));

                    algorithmMenu.getItems().add(bogoSortItem);
                }

                // ShellSort
                {
                    MenuItem bogoSortItem = new MenuItem("Shell Sort");
                    bogoSortItem.setOnAction(event -> sortingView.setSortingAlgorithm(new ShellSort(sortingView)));

                    algorithmMenu.getItems().add(bogoSortItem);
                }

                systemMenu.getItems().add(algorithmMenu);
            }

            // Toggle Stair mode
            {
                CheckMenuItem stairModeItem = new CheckMenuItem("Stair Mode?");
                stairModeItem.setSelected(true);
                stairModeItem.setOnAction(event -> {
                    sortingView.setStairMode(stairModeItem.isSelected());
                });
                systemMenu.getItems().add(stairModeItem);
            }

            systemMenu.getItems().add(new SeparatorMenuItem());
            // Exit
            {
                MenuItem exitItem = new MenuItem("Exit");
                exitItem.setOnAction(event -> {
                    try {
                        stopSorting();
                        Platform.exit();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                systemMenu.getItems().add(exitItem);
            }
            menuBar.getMenus().add(systemMenu);
        }

        return menuBar;
    }

    private void stopSorting() {
        sortingView.clear();
        sortingView.endSorting();
    }
}
