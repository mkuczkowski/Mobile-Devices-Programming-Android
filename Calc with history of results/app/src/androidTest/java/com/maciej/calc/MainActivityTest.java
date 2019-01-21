package com.maciej.calc;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.TextView;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    public static ViewAction setResultText(final String resultValue) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return allOf(isDisplayed(), isAssignableFrom(TextView.class));
            }

            @Override
            public String getDescription() {
                return "Result text updated";
            }

            @Override
            public void perform(UiController uiController, View view) {
                ((TextView) view).setText(resultValue);
            }
        };
    }

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void calculate() {
        onView(withId(R.id.display)).perform(setResultText("2+2*2"));
        onView(withId(R.id.buttonCalculate)).perform(click());
        String expectedResult = "6.0";
        onView(withId(R.id.display)).check(matches(withText(expectedResult)));
    }

    @Test
    public void appendValue() {
        onView(withId(R.id.display)).perform(setResultText("10"));
        onView(withId(R.id.button0)).perform(click());
        String expectedValue = "100";
        onView(withId(R.id.display)).check(matches(withText(expectedValue)));
    }

    @Test
    public void clearResultField() {
        onView(withId(R.id.display)).perform(setResultText("10+10*3"));
        onView(withId(R.id.buttonClear)).perform(click());
        String expectedValue = "";
        onView(withId(R.id.display)).check(matches(withText(expectedValue)));
    }

    @Test
    public void buttonOne() {
        onView(withId(R.id.button1)).perform(click());
        String valueToDisplay = "1";
        onView(withId(R.id.display)).check(matches(withText(valueToDisplay)));
    }

    @Test
    public void buttonTwo() {
        onView(withId(R.id.button2)).perform(click());
        String valueToDisplay = "2";
        onView(withId(R.id.display)).check(matches(withText(valueToDisplay)));
    }

    @Test
    public void buttonThree() {
        onView(withId(R.id.button3)).perform(click());
        String valueToDisplay = "3";
        onView(withId(R.id.display)).check(matches(withText(valueToDisplay)));
    }

    @Test
    public void buttonFour() {
        onView(withId(R.id.button4)).perform(click());
        String valueToDisplay = "4";
        onView(withId(R.id.display)).check(matches(withText(valueToDisplay)));
    }

    @Test
    public void buttonFive() {
        onView(withId(R.id.button5)).perform(click());
        String valueToDisplay = "5";
        onView(withId(R.id.display)).check(matches(withText(valueToDisplay)));
    }

    @Test
    public void buttonSix() {
        onView(withId(R.id.button6)).perform(click());
        String valueToDisplay = "6";
        onView(withId(R.id.display)).check(matches(withText(valueToDisplay)));
    }

    @Test
    public void buttonSeven() {
        onView(withId(R.id.button7)).perform(click());
        String valueToDisplay = "7";
        onView(withId(R.id.display)).check(matches(withText(valueToDisplay)));
    }

    @Test
    public void buttonEight() {
        onView(withId(R.id.button8)).perform(click());
        String valueToDisplay = "8";
        onView(withId(R.id.display)).check(matches(withText(valueToDisplay)));
    }

    @Test
    public void buttonNine() {
        onView(withId(R.id.button9)).perform(click());
        String valueToDisplay = "9";
        onView(withId(R.id.display)).check(matches(withText(valueToDisplay)));
    }

    @Test
    public void buttonZero() {
        onView(withId(R.id.button0)).perform(click());
        String valueToDisplay = "0";
        onView(withId(R.id.display)).check(matches(withText(valueToDisplay)));
    }

    @Test
    public void buttonMultiply() {
        onView(withId(R.id.buttonMultiply)).perform(click());
        String valueToDisplay = "*";
        onView(withId(R.id.display)).check(matches(withText(valueToDisplay)));
    }

    @Test
    public void buttonAdd() {
        onView(withId(R.id.buttonAdd)).perform(click());
        String valueToDisplay = "+";
        onView(withId(R.id.display)).check(matches(withText(valueToDisplay)));
    }

    @Test
    public void buttonSubstract() {
        onView(withId(R.id.buttonMinus)).perform(click());
        String valueToDisplay = "-";
        onView(withId(R.id.display)).check(matches(withText(valueToDisplay)));
    }

    @Test
    public void buttonDivide() {
        onView(withId(R.id.buttonDivide)).perform(click());
        String valueToDisplay = "/";
        onView(withId(R.id.display)).check(matches(withText(valueToDisplay)));
    }

    @Test
    public void buttonDot() {
        onView(withId(R.id.buttonDot)).perform(click());
        String valueToDisplay = ".";
        onView(withId(R.id.display)).check(matches(withText(valueToDisplay)));
    }
}