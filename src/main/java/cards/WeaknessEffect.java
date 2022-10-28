package cards;

import basemod.abstracts.CustomCard;
import cards.templates.AttackCardUnCommon;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.powers.AbstractPower;
import pathes.AbstractCardEnum;

/**
 * Date:2022/6/22
 * Author:Vent
 * Description:弱点特效
 **/
public class WeaknessEffect extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("WeaknessEffect");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
     public static final String UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/cards_Apex/WeaknessEffect.png";
    private static final int COST = 1;
    private static final int ATTACK_DMG = 0;
//     private static final int UPGRADE_PLUS_DMG = 10;
    public static final String ID = "WeaknessEffect";
    public WeaknessEffect() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.Apex_COLOR, CardRarity.UNCOMMON, CardTarget.ENEMY);
        // this.tags.add(BaseModCardTags.BASIC_STRIKE);
        this.baseDamage = ATTACK_DMG;
        this.baseMagicNumber = 2;
        //不能少，不然游戏默认初始值为-1！！！！！！
        this.magicNumber = baseMagicNumber;

    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // this.baseDamage += m.currentBlock * this.magicNumber;
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
    }

    public void calculateCardDamage(AbstractMonster mo) {
        if(!upgraded){
            if (mo.currentBlock != 0) {
                this.baseDamage = mo.currentBlock * this.magicNumber + 5;;
            }else {
                this.baseDamage = 5;
            }

            super.calculateCardDamage(mo);
            if (mo.currentBlock != 0) {
                this.baseDamage = mo.currentBlock * this.magicNumber + 5;;
            }else{
                this.baseDamage = 5;
            }
        }else{
            if (mo.currentBlock != 0) {
                this.baseDamage = mo.currentBlock * this.magicNumber + 10;;
            }else {
                this.baseDamage = 10;
            }

            super.calculateCardDamage(mo);
            if (mo.currentBlock != 0) {
                this.baseDamage = mo.currentBlock * this.magicNumber + 10;;
            }else{
                this.baseDamage = 10;
            }
        }


    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new WeaknessEffect();
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            //更改名字和提高2点伤害
            this.upgradeName();
            // this.upgradeDamage(UPGRADE_PLUS_DMG);
//            this.upgradeMagicNumber(1);
            this.rawDescription = UPGRADED_DESCRIPTION;
            this.initializeDescription();
        }

    }
}
