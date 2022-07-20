package helpers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.helpers.ImageMaster;

/**
 * Date:2022/6/23
 * Author:Vent
 * Description:
 **/
public class ApexImageMaster {
    public static Texture[] APEX_DEFAULT = new Texture[10];

    public static void initialize() {
        int i;
        for (int j = 0; j < 10; j++) {
            APEX_DEFAULT[j] = ImageMaster.loadImage("img/char_Apex/default/" + (j + 1) + ".png");
        }
    }
}
