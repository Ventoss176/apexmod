package relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import powers.SchemePower;

/**
 * Date:2022/6/22
 * Author:Vent
 * Description:锦囊
 **/
public class SilkBag extends CustomRelic {
    public static final String ID = "SilkBag";
    private static final String IMG = "img/relics_Apex/SilkBag.png";
    private static final String IMG_OTL = "img/relics_Apex/outline/SilkBag.png";
    public SilkBag() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.COMMON, LandingSound.CLINK);
    }

    @Override
    public void atBattleStart() {
        //在战斗开始时触发
        this.flash();
        this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new SchemePower(AbstractDungeon.player, 3), 3));
        this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }



}
