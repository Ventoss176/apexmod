package cards;

import basemod.abstracts.CustomCard;
import cards.tempCards.Strategy;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import pathes.AbstractCardEnum;
import powers.SchemePower;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Date:2022/6/21
 * Author:Vent
 * Description:阳谋根据计谋点造成伤害
 **/
public class Yangmou extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Yangmou");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards_Apex/Yangmou.png";
    private static final int COST = 0;
    private static final int MAGNIFICATION = 3;
    // private static final int UPGRADE_PLUS_DMG = 3;
    public static final String ID = "Yangmou";

    public Yangmou() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.Apex_COLOR, CardRarity.COMMON, CardTarget.ENEMY);
        // this.tags.add(BaseModCardTags.BASIC_STRIKE);
        //倍率
        this.baseDamage = 0;
        this.baseMagicNumber = MAGNIFICATION;
        this.magicNumber = this.baseMagicNumber;
        this.cardsToPreview = new Strategy();
    }

    @Override
    public void upgrade() {
        if(!upgraded){
            this.upgradeName();
            // this.upgradeBaseCost(0);
            this.initializeDescription();
            this.upgradeMagicNumber(2);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // AbstractPower scheme = AbstractDungeon.player.getPower("Scheme");
        // if (scheme != null) {
        //     this.damage = scheme.amount * this.magicNumber + this.baseDamage;
        // }
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
    }

    public void applyPowers() {
        AbstractPower scheme = AbstractDungeon.player.getPower("Scheme");
        if (scheme != null && scheme.amount > 0) {
            this.baseDamage = scheme.amount * this.magicNumber;
        }else {
            this.baseDamage = 0;

        }

        super.applyPowers();
        if (scheme != null && scheme.amount > 0) {
            this.baseDamage = scheme.amount * this.magicNumber;
        }else{

            this.baseDamage = 0;
        }

    }

    @Override
    public void triggerOnEndOfPlayerTurn() {
        this.baseDamage = 0;
    }

    public void calculateCardDamage(AbstractMonster mo) {
        AbstractPower scheme = AbstractDungeon.player.getPower("Scheme");
        if (scheme != null) {
            this.baseDamage = scheme.amount * this.magicNumber;
        }

        super.calculateCardDamage(mo);
        if (scheme != null) {
            this.baseDamage = scheme.amount * this.magicNumber;
        }

    }


    @Override
    public AbstractCard makeCopy() {
        return new Yangmou();
    }
}
