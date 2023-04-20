package com.mobile.pablo.iosnotes.ext

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.util.HumanReadables
import androidx.test.espresso.util.TreeIterables
import java.util.concurrent.TimeoutException
import org.hamcrest.Matcher

fun viewIsDisplayed(viewId: Int): ViewInteraction = onView(withId(viewId)).check(matches(isDisplayed()))

fun viewIsDisplayed(view: Matcher<View>): ViewInteraction = onView(view).check(matches(isDisplayed()))

fun waitForViewIsDisplayed(
    viewId: Int,
    millis: Long = 5000L
): ViewInteraction = onView(withId(viewId)).perform(
    waitId(
        viewId,
        millis
    )
)

// https://stackoverflow.com/questions/49796132/android-espresso-wait-for-text-to-appear
// https://www.repeato.app/espresso-wait-for-view/
fun waitId(
    viewId: Int,
    millis: Long = 5000L
): ViewAction {
    return object : ViewAction {
        override fun getConstraints(): Matcher<View> {
            return isRoot()
        }

        override fun getDescription(): String {
            return "wait for a specific view with id <$viewId> during $millis millis."
        }

        override fun perform(
            uiController: UiController,
            view: View
        ) {
            uiController.loopMainThreadUntilIdle()
            val startTime = System.currentTimeMillis()
            val endTime = startTime + millis
            val viewMatcher = withId(viewId)
            do {
                for (child in TreeIterables.breadthFirstViewTraversal(view)) {
                    // found view with required ID
                    if (viewMatcher.matches(child)) {
                        return
                    }
                }
                uiController.loopMainThreadForAtLeast(50)
            } while (System.currentTimeMillis() < endTime)
            throw PerformException.Builder()
                .withActionDescription(this.description)
                .withViewDescription(HumanReadables.describe(view))
                .withCause(TimeoutException())
                .build()
        }
    }
}

fun sleepView(millis: Long = 3000L) {
    try {
        Thread.sleep(millis)
    } catch (e: InterruptedException) {
        e.printStackTrace()
    } catch (e: IllegalArgumentException) {
        e.printStackTrace()
    }
}
