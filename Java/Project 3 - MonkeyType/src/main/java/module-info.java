module org.project {

	requires javafx.base;
	requires javafx.controls;

	opens org.project to javafx.graphics;
}