package cards;

import actions.StargazingAction;
import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jdk.nashorn.internal.ir.Block;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pathes.AbstractCardEnum;
import pathes.ApexTags;
import relics.Checkerboard;

import java.util.Iterator;

/**
 * Date:2022/6/20
 * Author:Vent
 * Description:
 **/
public class Formation extends CustomCard {
    // private Checkerboard checkerboard;
    private static final Logger logger = LogManager.getLogger(Provoke.class.getName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Formation");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/cards_Apex/Formation.png";
    private static final int COST = 1;
    private static final int BLOCK = 8;
    private static final int UPGRADE_PLUS_M = 3;
    public static final String ID = "Formation";
    private static int count = 0;

    public Formation() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.Apex_COLOR, CardRarity.BASIC, CardTarget.SELF);
        // this.tags.add(car);
        this.tags.add(ApexTags.STAR);
        this.baseBlock = BLOCK;

        this.baseMagicNumber = 5;
        this.magicNumber = baseMagicNumber;
    }


    // @Override
    // public void triggerWhenDrawn() {
    //     count++;
    //     this.baseBlock += count;
    //     super.triggerWhenDrawn();
    // }
    //
    // @Override
    // public void triggerOnEndOfPlayerTurn() {
    //     count = 0;
    //     this.baseBlock = BLOCK;
    //     super.triggerOnEndOfPlayerTurn();
    // }


    @Override
    public void upgrade() {
        //卡牌升级后的效果
        if (!this.upgraded) {
            //更改名字和提高3点格挡
            this.upgradeName();
            this.upgradeBlock(UPGRADE_PLUS_M);
            this.initializeDescription();
            // this.upgradeBaseCost(0);
        }

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)p, (AbstractCreature)p, this.block));
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new StargazingAction(this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new Formation();
    }

}
