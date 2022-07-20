package powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

/**
 * Date:2022/6/22
 * Author:Vent
 * Description:
 **/
public class TrumpPower extends AbstractPower {
    public static final String POWER_ID = "Trump";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public TrumpPower(AbstractCreature owner, int loseEnergyAmt) {
        this.name = NAME;
        this.ID = "Trump";
        this.owner = owner;
        this.amount = loseEnergyAmt;
        if (this.amount >= 999) {
            this.amount = 999;
        }
        this.type = PowerType.DEBUFF;

        this.updateDescription();
        this.loadRegion("fasting");
    }

    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        if (this.amount >= 999) {
            this.amount = 999;
        }

    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurn() {
        this.addToBot(new LoseEnergyAction(this.amount));
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "Trump"));
        // this.amount = 0;
        this.flash();
    }



}
