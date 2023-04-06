package cards;

import actions.TooFocusAction;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import apexpathes.AbstractCardEnum;

import java.util.Iterator;

/**
 * Date:2022/6/22
 * Author:Vent
 * Description:
 **/
public class TooFocus extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("TooFocus");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/cards_Apex/TooFocus.png";
    private static final int COST = 1;
    private static final int ATTACK_DMG = 6;
    private static final int UPGRADE_PLUS_DMG = 3;
    public static final String ID = "TooFocus";
    public TooFocus() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.Apex_COLOR, CardRarity.UNCOMMON, CardTarget.ENEMY);
        // this.tags.add(BaseModCardTags.BASIC_STRIKE);
        // this.tags.add(ApexTags.Batto);
        this.baseDamage = ATTACK_DMG;
        // this.baseMagicNumber = 7;
        //不能少，不然游戏默认初始值为-1！！！！！！
        // this.magicNumber = baseMagicNumber;

    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new TooFocusAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        Iterator var1 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();

        while(var1.hasNext()) {
            AbstractMonster m = (AbstractMonster)var1.next();
            if (!m.isDeadOrEscaped() && (m.hasPower("PathToVictoryPower") ||
                    m.hasPower("Poison") || m.hasPower("Artifact") || m.hasPower("NewMarkPower"))) {
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
                break;
            }
        }

    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new TooFocus();
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            //更改名字和提高2点伤害
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
            this.initializeDescription();
            // this.upgradeMagicNumber(2);
        }

    }
}
