package powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
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
public class SwordMasterPower extends AbstractPower {
    public static final String POWER_ID = "SwordMaster";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private int damage;

    public SwordMasterPower(AbstractCreature owner, int newAmount) {
        this.name = powerStrings.NAME;
        this.ID = "SwordMaster";
        this.owner = owner;
        this.amount = newAmount;
        this.updateDescription();
        this.loadRegion("flameBarrier");
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
    }

    public void atStartOfTurnPostDraw() {
        this.flash();
         if (!AbstractDungeon.player.hasPower("HeartWater")) {
//             this.addToBot(new ChangeStanceAction("Divinity"));
            this.addToBot(new ApplyPowerAction(this.owner, this.owner, new SheathPower(this.owner, this.amount), this.amount));
            this.addToBot(new ApplyPowerAction(this.owner, this.owner, new HeartWaterPower(this.owner, this.amount), this.amount));
             this.updateDescription();
         }else if(AbstractDungeon.player.hasPower("HeartWater")) {
             this.addToBot(new ApplyPowerAction(this.owner, this.owner, new SheathPower(this.owner, this.amount), this.amount));
            this.updateDescription();
         }


    }
}
