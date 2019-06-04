package bundles;

import java.util.ListResourceBundle;

public class Bundle_pl_PL extends ListResourceBundle {

    static final Object[][] contents = {
            {"autor1","Michal Goleniewski"},
            {"autor2","Przemyslaw Lapinski"},

    };
    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
