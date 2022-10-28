package powers;

import cards.tempCards.Conspiracy;
import cards.tempCards.Trickery;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

/**
 * Date:2022/6/21
 * Author:Vent
 * Description:
 **/
public class BrainstormPower extends AbstractPower {
    public static final String POWER_ID = "Brainstorm";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public BrainstormPower(AbstractCreature owner, int numTurns) {
        this.name = NAME;
        this.ID = "Brainstorm";
        this.owner = owner;
        this.amount = numTurns;
        this.isTurnBased = true;
        if (this.amount >= 999) {
            this.amount = 999;
        }

        this.updateDescription();
        this.loadRegion("energized_blue");
    }

    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        if (this.amount >= 999) {
            this.amount = 999;
        }

    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    public void onEnergyRecharge() {
        this.flash();
         AbstractCard card1 = new Conspiracy();
        AbstractCard card2 = new Trickery();
        // card.upgrade();
         this.addToBot(new MakeTempCardInHandAction(card1));
//        this.addToBot(new MakeTempCardInHandAction(card2));
        this.addToBot(new MakeTempCardInHandAction(card2));
        if (this.amount <= 1) {
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "Brainstorm"));
        } else {
            this.addToBot(new ReducePowerAction(this.owner, this.owner, "Brainstorm", 1));
        }
    }

    // static {
    //     powerStrings = CardCrawlGame.languagePack.getPowerStrings("Collect");
    //     NAME = powerStrings.NAME;
    //     DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    // }
}
