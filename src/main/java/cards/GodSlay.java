package cards;

import actions.LaidoAttackAction;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import apexpathes.AbstractCardEnum;
import apexpathes.ApexTags;

/**
 * Date:2022/6/22
 * Author:Vent
 * Description:
 **/
public class GodSlay extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("GodSlay");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/cards_Apex/GodSlay.png";
    private static final int COST = 3;
    private static final int ATTACK_DMG = 30;
    private static final int UPGRADE_PLUS_DMG = 8;
    public static final String ID = "GodSlay";
    public GodSlay() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.Apex_COLOR, CardRarity.RARE, CardTarget.ENEMY);
        // this.tags.add(BaseModCardTags.BASIC_STRIKE);
        this.tags.add(ApexTags.Batto);
        this.baseDamage = ATTACK_DMG;
        this.isEthereal = true;
        // this.baseMagicNumber = 1;
        //不能少，不然游戏默认初始值为-1！！！！！！
        // this.magicNumber = baseMagicNumber;

    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        this.addToBot(new LaidoAttackAction(m,p, new DamageInfo(p, this.damage, this.damageTypeForTurn)));
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new GodSlay();
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            //更改名字和提高2点伤害
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
            this.isEthereal = false;
            this.rawDescription = UPGRADED_DESCRIPTION;
            this.initializeDescription();
            // this.upgradeMagicNumber(1);
        }

    }
}
