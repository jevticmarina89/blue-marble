package hellofx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDate;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.curiousworks.BlueMarble;
import org.json.JSONObject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BlueMarbleController {

	@FXML
	private ImageView image;

	@FXML
	private DatePicker datePicker;
	
    @FXML
    private CheckBox enhancedImage;

    @FXML
    private CheckBox blackAndWhiteImage;

	@FXML
	void updateDate(ActionEvent event) {
		
		LocalDate today = LocalDate.now();
		LocalDate picked = LocalDate.of(datePicker.getValue().getYear(), datePicker.getValue().getMonthValue(), datePicker.getValue().getDayOfMonth());
		
		try {
			if (picked.isAfter(today))
				throw new Exception();
			BlueMarble blueMarble = new BlueMarble();

			if ((datePicker.getValue().getYear() > 2018
					|| (datePicker.getValue().getYear() == 2018 && datePicker.getValue().getMonthValue() > 6))
					&& enhancedImage.isSelected()) {
				enhancedImage.setSelected(false);
			}
//		blueMarble.setDate(datePicker.getValue().getYear() + "-0" + datePicker.getValue().getMonthValue() + "-" + datePicker.getValue().getDayOfMonth());
			blueMarble.setDate(
					"2018-0" + datePicker.getValue().getMonthValue() + "-" + datePicker.getValue().getDayOfMonth());
			blueMarble.setEnhanced(enhancedImage.isSelected());
//		Image value = new Image(BlueMarble.getMostRecentImage());
			image.setImage(new Image(blueMarble.getImage()));
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid date!");
			alert.showAndWait();
		}
	}

	
    @FXML
    void viewOfBlackAndWhiteImage(ActionEvent event) {
		if (image.getImage() != null) {
			if (blackAndWhiteImage.isSelected()) {
				datePicker.getOnAction();
				ColorAdjust adjust = new ColorAdjust();
				adjust.setSaturation(-1);
				//adjust.setContrast(1);
				image.setEffect(adjust);
			} else {
				image.setEffect(null);
			}
		}

    }


}
