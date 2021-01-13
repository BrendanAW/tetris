package com.epam.prejap.tetris.game;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(groups = "NavigationKeys")
public class MoveTest {

    @Test(dataProvider = "keys")
    public void navigationKeysShouldBeConfigurable(char[] providedKeys) {
        //given
        char[] defaultKeys = Move.DEFAULT_KEYS;

        //when
        char[] actualKeys = Move.modifyNavigationKeys(providedKeys);

        //then
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotEquals(actualKeys, defaultKeys);
        softAssert.assertNotEquals(Move.of(defaultKeys[1]), Move.LEFT);
        softAssert.assertNotEquals(Move.of(defaultKeys[2]), Move.RIGHT);
        softAssert.assertAll("Navigation keys should have been configured but were not and are still default.");
    }

    @Test(dataProvider = "keys")
    public void navigationKeysCanBeModified(char[] providedKeys) {
        //given

        //when
        char[] actualKeys = Move.modifyNavigationKeys(providedKeys);

        //then
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualKeys, providedKeys);
        softAssert.assertEquals(Move.of(actualKeys[0]), Move.NONE);
        softAssert.assertEquals(Move.of(actualKeys[1]), Move.LEFT);
        softAssert.assertEquals(Move.of(actualKeys[2]), Move.RIGHT);
        softAssert.assertAll("Navigation keys should have been modified to provided ones but were not.");
    }

    @DataProvider
    public static Object[] keys() {
        return new Object[]{
                new char[]{'a', 's', 'd'},
                new char[]{',', '.', '/'},
                new char[]{'~', '!', '@'},
                new char[]{'1', '2', '3'}
        };
    }
}
