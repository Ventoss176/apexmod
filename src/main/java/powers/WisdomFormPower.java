package powers;

import actions.CostReduction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import pathes.ApexTags;

/**
 * @authoer:Ventoss
 * @createDate:2022/10/31
 * @description:
 */
public class WisdomFormPower extends AbstractPower {
    public static final String POWER_ID = "WisdomForm";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public WisdomFormPower(AbstractCreature owner, int amt) {
        this.name = powerStrings.NAME;
        this.ID = "WisdomForm";
        this.owner = owner;
        this.amount = amt;
        this.updateDescription();
        this.loadRegion("ai");
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

//    @Override
//    public void atStartOfTurn() {
//        this.addToBot(new PutOnDeckAction(this.owner, this.owner, this.amount, false));
//    }

//    @Override
//    public void atStartOfTurnPostDraw() {
//        this.flash();
////        this.addToBot(new PutOnDeckAction(this.owner, this.owner, this.amount, false));
//        this.addToBot(new GainEnergyAction(1));
//        this.addToBot(new DrawCardAction(2));
//        this.addToTop(new CostReduction(this.owner, 99, true));
//    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (!card.purgeOnUse && this.amount > 0 && AbstractDungeon.actionManager.cardsPlayedThisTurn.size() == 1) {
                CardCrawlGame.sound.play("STANCE_ENTER_CALM");
                this.addToBot(new GainEnergyAction(1));
                this.addToBot(new DrawCardAction(2));
                this.addToTop(new CostReduction(this.owner, 99, true));
            }

    }




    @Override
    public void atEndOfRound() {
        this.flash();
        if (this.amount == 0) {
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "WisdomForm"));
        } else {
            this.addToBot(new ReducePowerAction(this.owner, this.owner, "WisdomForm", 1));
        }

    }
}
