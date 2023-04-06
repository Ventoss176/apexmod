package relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

/**
 * Date:2022/6/22
 * Author:Vent
 * Description:
 **/
public class Yamato extends CustomRelic {
    public static final String ID = "Yamato";
    private static final String IMG = "img/relics_Apex/Yamato.png";
    private static final String IMG_OTL = "img/relics_Apex/outline/Yamato.png";
    public Yamato() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.BOSS, AbstractRelic.LandingSound.MAGICAL);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new Yamato();
    }

//    public float atDamageModify(float damage, AbstractCard c) {
//        return c.hasTag(ApexTags.Batto) && (c.type == AbstractCard.CardType.ATTACK) ? damage + 10 : damage;
//    }

}
