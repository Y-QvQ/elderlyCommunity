module elderlyCommunity {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.base;
	requires com.google.gson;

	exports model;

	opens model to com.google.gson, javafx.base;
	opens controller to javafx.fxml, javafx.graphics;

}
