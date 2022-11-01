package powers;

import actions.CostReduction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

/**
 * Date:2022/6/20
 * Author:Vent
 * Description:
 **/
public class SchemePower extends AbstractPower {
    // public static final String POWER_ID = "Scheme";
    // private static final PowerStrings powerStrings;
    private final int PRAYER_REQUIRED = 10;
    // 能力的ID
    public static final String POWER_ID = "Scheme";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static int quickScheme;


    public SchemePower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "Scheme";
        this.owner = owner;
        this.amount = amount;
        if (this.amount <= -999) {
            this.amount = -999;
        }

        if (AbstractDungeon.player.hasPower("MindControl")) {
            int mindControls = AbstractDungeon.player.getPower("MindControl").amount;
            this.amount += mindControls;
        }

        this.updateDescription();
        this.loadRegion("mantra");
        // this.type = PowerType.BUFF;
        // GameActionManager var10000 = AbstractDungeon.actionManager;
        // var10000.mantraGained += amount;
        this.canGoNegative = true;

    }

    public void playApplyPowerSfx() {
        CardCrawlGame.sound.play("POWER_MANTRA", 0.05F);
    }

    public void updateDescription() {

        if (this.amount > 0) {
            this.description = DESCRIPTIONS[0];
            this.type = PowerType.BUFF;
        } else {
            this.description = DESCRIPTIONS[0];
            this.type = PowerType.DEBUFF;
        }

    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;

        if (AbstractDungeon.player.hasPower("MindControl")) {
            int mindControls = AbstractDungeon.player.getPower("MindControl").amount;
            this.amount += mindControls;
        }

        qiceFunction(this.amount);


        if (this.amount <= -999) {
            this.amount = -999;
        }
        if (this.amount == 0) {
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "Scheme"));
        }


    }

    public void qiceFunction(int sAmount){
        if (this.amount >= 8) {
            CardCrawlGame.sound.play("STANCE_DIVINITY", 0.05F);
            this.addToBot(new GainEnergyAction(1));
            this.addToBot(new DrawCardAction(2));
            this.addToTop(new CostReduction(this.owner, 99, true));
            this.amount -= quickScheme;
            if(this.amount != 0){
                qiceFunction(this.amount);
            }
            if (this.amount == 0) {
                this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "Scheme"));
            }
        }

    }

    public void reducePower(int reduceAmount) {
        this.fontScale = 8.0F;
        this.amount -= reduceAmount;

        qiceFunction(this.amount);

        if (this.amount == 0) {
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "Scheme"));

        }

        if (this.amount >= 999) {
            this.amount = 999;
        }

        if (this.amount <= -999) {
            this.amount = -999;
        }

    }
}
    // static {
    //     powerStrings = CardCrawlGame.languagePack.getPowerStrings("Scheme");
    // }
