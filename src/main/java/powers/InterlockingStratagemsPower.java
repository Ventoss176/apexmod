package powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.MantraPower;

/**
 * Date:2022/6/22
 * Author:Vent
 * Description:
 **/
public class InterlockingStratagemsPower extends AbstractPower {
    public static final String POWER_ID = "InterlockingStratagems";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private int damage;

    public InterlockingStratagemsPower(AbstractCreature owner, int newAmount) {
        this.name = powerStrings.NAME;
        this.ID = "InterlockingStratagems";
        this.owner = owner;
        this.amount = newAmount;
        this.updateDescription();
        this.loadRegion("devotion");
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    public void atStartOfTurnPostDraw() {
        this.flash();
        // if (!AbstractDungeon.player.hasPower("Scheme") && this.amount >= 8) {
        //     // this.addToBot(new ChangeStanceAction("Divinity"));
        //     this.updateDescription();
        // }else if(AbstractDungeon.player.hasPower("Scheme") && this.amount >= 8){
        //     this.updateDescription();
        // }else if(!AbstractDungeon.player.hasPower("Scheme")) {
        // }
        this.addToBot(new ApplyPowerAction(this.owner, this.owner, new SchemePower(this.owner, this.amount), this.amount));


    }
}
