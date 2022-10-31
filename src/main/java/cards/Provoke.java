package cards;

import actions.CostReduction;
import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.MummifiedHand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pathes.AbstractCardEnum;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Date:2022/6/20
 * Author:Vent
 * Description:
 **/
public class Provoke extends CustomCard {
    private static final Logger logger = LogManager.getLogger(Provoke.class.getName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Provoke");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/cards_Apex/Provoke.png";
    private static final int COST = 2;
    private static final int ATTACK_DMG = 9;
    private static final int UPGRADE_PLUS_DMG = 3;
    public static final String ID = "Provoke";

    public Provoke() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.Apex_COLOR, CardRarity.BASIC, CardTarget.ENEMY);
        this.baseDamage = ATTACK_DMG;
        this.magicNumber = this.baseMagicNumber = 1;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            //更改名字和提高3点伤害
            this.upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            this.rawDescription = UPGRADED_DESCRIPTION;
            initializeDescription();
        }

    }

    @Override
    public void use(AbstractPlayer P, AbstractMonster M) {
        this.addToBot(new DamageAction(M, new DamageInfo(P, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        if(this.upgraded){
            AbstractDungeon.actionManager.addToBottom(new CostReduction(P, this.magicNumber, false));
        }else{
            this.addToBot(new CostReduction(P, this.magicNumber, true));

        }
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new Provoke();
    }
}
