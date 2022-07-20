package powers;

import cards.*;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import pathes.ApexTags;

import java.util.Iterator;

/**
 * Date:2022/6/22
 * Author:Vent
 * Description:严刑拷打
 **/
public class TorturePower extends AbstractPower {
    public static final String POWER_ID = "Torture";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    public TorturePower(AbstractCreature owner, int amount) {
        this.name = powerStrings.NAME;
        this.ID = "Torture";
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.loadRegion("choke");
        this.type = PowerType.BUFF;
    }

    public void atStartOfTurn() {
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "Torture"));
    }

    // public void onUseCard(AbstractCard card, UseCardAction action) {
    //     if(card.costForTurn == 0 && card.type == AbstractCard.CardType.ATTACK){
    //         System.out.println("是否使用0费 攻击牌");
    //         this.amount *= 2;
    //     }
    // }


    // @Override
    // public void stackPower(int stackAmount) {
    //     super.stackPower(stackAmount);
    // }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (c.costForTurn == 0 && c.type == AbstractCard.CardType.ATTACK) {
            this.addToBot(new ApplyPowerAction(m, AbstractDungeon.player, new VulnerablePower(m, this.amount, false), this.amount));
        }
    }

    public void updateDescription () {
            this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
    }

}