package powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

/**
 * Date:2022/6/21
 * Author:Vent
 * Description:
 **/
public class LoneWolfAPower extends AbstractPower {
    public static final String POWER_ID = "LoneWolfA";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private int damage;

    public LoneWolfAPower(AbstractCreature owner, int damage) {
        this.name = NAME;
        this.ID = "Panache";
        this.owner = owner;
        this.amount = 5;
        this.damage = damage;
        this.updateDescription();
        this.loadRegion("panache");
    }

    public void updateDescription() {
        // if (this.amount == 1) {
            this.description = DESCRIPTIONS[0];
        // } else {
        //     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[3] + this.damage + DESCRIPTIONS[2];
        // }

    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.damage += stackAmount;
        this.amount += stackAmount;
        this.updateDescription();
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        // --this.amount;
        // if (this.amount == 0) {
        //     this.flash();
        //     this.amount = 5;
        if(card.type == AbstractCard.CardType.SKILL){
            this.addToBot(new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(this.damage, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
        // }

        this.updateDescription();
    }

    // public void atStartOfTurn() {
    //     this.amount = 5;
    //     this.updateDescription();
    // }

}
