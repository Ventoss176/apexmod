package powers;

import cards.tempCards.Conspiracy;
import cards.tempCards.Trickery;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.tempCards.Smite;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;
import java.util.Random;

/**
 * Date:2022/6/22
 * Author:Vent
 * Description:
 **/
public class SchemeAIntriguePower extends AbstractPower {
    public static final String POWER_ID = "SchemeAIntrigue";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private int damage;


    public SchemeAIntriguePower(AbstractCreature owner, int amt) {
        this.name = NAME;
        this.ID = "SchemeAIntrigue";
        this.owner = owner;
        this.amount = amt;
        this.updateDescription();
        this.loadRegion("deva2");
    }

    public void atStartOfTurn() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();
//            AbstractCard card1 = new Conspiracy();
            AbstractCard card2 = new Trickery();
//            this.addToBot(new MakeTempCardInHandAction(card1, this.amount, false));
            this.addToBot(new MakeTempCardInHandAction(card2, this.amount, false));
        }

    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    public void updateDescription() {

        if (Settings.language == Settings.GameLanguage.ZHS) {

            this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];

        } else {
            if (this.amount == 1){

                this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
            }else {

                this.description = powerStrings.DESCRIPTIONS[2] + this.amount + powerStrings.DESCRIPTIONS[3];

            }
        }


    }
}
