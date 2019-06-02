package bundles;


import java.util.ListResourceBundle;

public class Bundle_pl_PL extends ListResourceBundle {
    static final Object[][] contents = {
            {"autor","Autorzy"},
            {"level one","Poziom Łatwy"},
            {"level two","Poziom Średni"},
            {"level three","Poziom Trudny"},
    };
    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
