package relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import pathes.ApexTags;

/**
 * Date:2022/6/23
 * Author:Vent
 * Description:
 **/
public class CamBow extends CustomRelic{
    public static final String ID = "CamBow";
    private static final String IMG = "img/relics_Apex/LongBow.png";
    private static final String IMG_OTL = "img/relics_Apex/outline/LongBow.png";
    public CamBow() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.BOSS, LandingSound.CLINK);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new CamBow();
    }

//    public float atDamageModify(float damage, AbstractCard c) {
//        return c.hasTag(ApexTags.ARROW) && (c.type == AbstractCard.CardType.ATTACK) ? damage + (damage * 0.5F)  : damage;
//    }
}
