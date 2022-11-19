package powers;

import basemod.helpers.BaseModCardTags;
import basemod.helpers.CardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import pathes.ApexTags;

/**
 * Date:2022/6/21
 * Author:Vent
 * Description:
 **/
public class JueShengQianLiPower extends AbstractPower {
    public static final String POWER_ID = "JueShengQianLi";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public JueShengQianLiPower(AbstractCreature owner, int amt) {
        this.name = powerStrings.NAME;
        this.ID = "JueShengQianLi";
        this.owner = owner;
        this.amount = amt;
        this.updateDescription();
        this.loadRegion("nirvana");
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurn() {
        if(AbstractDungeon.player.hasPower("DeviseStrategies")){
            this.addToBot(new GainBlockAction(this.owner, this.amount));
        }
        if(AbstractDungeon.player.hasPower("Purposeful")){
            int pAmount = AbstractDungeon.player.getPower("Purposeful").amount;
            this.addToBot(new GainBlockAction(this.owner, this.amount * pAmount));
        }
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if(card.hasTag(ApexTags.STAR) ||
                card.hasTag(ApexTags.ARROW) ||
                card.cardID == "Setup" ||
                card.cardID == "GodGuide" ||
                card.cardID == "Warcry" ||
                card.cardID == "Forethought" ||
                card.cardID == "Thinking Ahead" ||
                card.cardID == "SickWork" ||
                card.cardID == "SkillSword" ||
                card.cardID == "BackupDefense" ){
            this.addToBot(new GainBlockAction(this.owner, this.amount));
        }

    }
}
