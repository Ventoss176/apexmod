package cards;

import basemod.abstracts.CustomCard;
import cards.tempCards.Strategy;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import apexpathes.AbstractCardEnum;
import powers.SchemePower;

/**
 * Date:2022/6/21
 * Author:Vent
 * Description:
 **/
public class AbsoluteForce extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("AbsoluteForce");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/cards_Apex/AbsoluteForce.png";
    private static final int COST = 2;
    private static final int ATTACK_DMG = 21;
    private static final int UPGRADE_PLUS_DMG = 8;
    public static final String ID = "AbsoluteForce";
    public AbsoluteForce() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.Apex_COLOR, CardRarity.UNCOMMON, CardTarget.ENEMY);
        // this.tags.add(BaseModCardTags.BASIC_STRIKE);
        this.baseDamage = ATTACK_DMG;
        this.baseMagicNumber = 1;
        //不能少，不然游戏默认初始值为-1！！！！！！
        this.magicNumber = baseMagicNumber;
        this.cardsToPreview = new Strategy();

    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //使用卡牌时触发的动作
        this.addToBot(new ApplyPowerAction(p, p, new SchemePower(p, -this.magicNumber), -this.magicNumber));
        this.addToBot(new WaitAction(0.1F));
        // if (p != null && m != null) {
        //     this.addToBot(new VFXAction(new IronWaveEffect(p.hb.cX, p.hb.cY, m.hb.cX), 0.5F));
        // }

        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));


        // this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new AbsoluteForce();
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            //更改名字和提高2点伤害
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
            this.upgradeMagicNumber(1);
            this.initializeDescription();
        }

    }
}
