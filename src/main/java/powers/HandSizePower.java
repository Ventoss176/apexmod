package powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

/**
 * @authoer:Ventoss
 * @createDate:2022/11/6
 * @description:
 */
public class HandSizePower extends AbstractPower {
    public static final String POWER_ID = "HandSizePower";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private int damage;

    public HandSizePower(AbstractCreature owner, int Amount) {
        this.name = powerStrings.NAME;
        this.ID = "HandSizePower";
        this.owner = owner;
        this.amount = Amount;
        this.updateDescription();
        this.loadRegion("brutality");
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;


        if (this.amount >= 999) {
            this.amount = 999;
        }
        if (this.amount == 0) {
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "HandSizePower"));
        }


    }


}
