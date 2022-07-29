package models.components;

import java.lang.reflect.Constructor;

public class AnnotationTest {

    public <T> void getComponentCssSelector(Class<T> componentClass) {

        try {
            String cssSelector = componentClass.getAnnotation(ComponentCssSelector.class).value();
            System.out.println(cssSelector);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new AnnotationTest().getComponentCssSelector(LoginFormComponent.class);

    }
}
