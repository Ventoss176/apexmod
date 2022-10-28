package powers;

import actions.CostReduction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

/**
 * @authoer:Ventoss
 * @createDate:2022/10/28
 * @description:
 */
public class HeartWaterPower extends AbstractPower{
    public static final String POWER_ID = "HeartWater";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public HeartWaterPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "HeartWater";
        this.owner = owner;
        this.amount = amount;
        if (this.amount <= 0) {
            this.amount = 0;
        }

        this.updateDescription();
        this.loadRegion("wave_of_the_hand");
        // this.type = PowerType.BUFF;
        // GameActionManager var10000 = AbstractDungeon.actionManager;
        // var10000.mantraGained += amount;
//        this.canGoNegative = true;

    }



    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;

        if (this.amount >= 999) {
            this.amount = 999;
        }
        if (this.amount <= 0) {
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "HeartWater"));
        }


    }

    public void reducePower(int reduceAmount) {
        this.fontScale = 8.0F;
        this.amount -= reduceAmount;
        if (this.amount <= 0) {
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "HeartWater"));

        }

        if (this.amount >= 999) {
            this.amount = 999;
        }


    }
}
