package com.epam.examples.runner;

import com.epam.examples.steps.SearchSteps;
import com.epam.tests.pages.PageFactory;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.executors.SameThreadExecutors;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.web.selenium.PerStoriesWebDriverSteps;
import org.jbehave.web.selenium.PerStoryWebDriverSteps;
import org.jbehave.web.selenium.WebDriverScreenshotOnFailure;
import org.jbehave.web.selenium.WebDriverSteps;

import java.util.List;

import static org.jbehave.core.io.CodeLocations.codeLocationFromPath;

// Import library for InternetExplorerDriver

public class SearchTestRunner extends JUnitStories {

    public static final String STORIES_RELATIVE_DIRECTORY = "src/test/resources";
    public static final String REPORT_RELATIVE_DIRECTORY = "../build/reports/jbehave";
    public static final String STORY_TO_EXCLUDE = "**/exclude_*.story";



    // Add InternetExplorerDriver
    //private WebDriver driver = new InternetExplorerDriver();


    private PropertyWebDriverProvider driver = new PropertyWebDriverProvider();

    //  Starting and stopping the browser
    private WebDriverSteps lifecycleSteps = new PerStoriesWebDriverSteps(
            driver);


    private PageFactory pageFactory = new PageFactory(driver);

    public SearchTestRunner() {
        if (lifecycleSteps instanceof PerStoryWebDriverSteps) {
            configuredEmbedder().useExecutorService(new SameThreadExecutors().create(
                configuredEmbedder().embedderControls()));
        }
        configuredEmbedder().embedderControls().useStoryTimeoutInSecs(
            1000);
    }

    @Override
    // How to run stories and report on them
    public Configuration configuration() {

        return new MostUsefulConfiguration().useStoryLoader(
                // Search the story (in the same directory as the class)
            new LoadFromClasspath(getClass().getClassLoader()))
                                            .useStoryReporterBuilder(
                                                new StoryReporterBuilder()
                                                    .withFormats(Format.XML, Format.STATS, Format.CONSOLE,
                                                        Format.HTML));
    }
    // Classes with steps description
    public InstanceStepsFactory stepsFactory() {
        Configuration configuration = configuration();
        return new InstanceStepsFactory(configuration, new SearchSteps(
            pageFactory), lifecycleSteps, new WebDriverScreenshotOnFailure(
                driver, configuration.storyReporterBuilder()));
    }

    @Override
    // Where to find the stories to run
    protected List<String> storyPaths() {
        String storyToInclude = "**/" + System.getProperty("story", "*")
            + ".story";
        return new StoryFinder().findPaths(
            codeLocationFromPath(STORIES_RELATIVE_DIRECTORY),
            storyToInclude, STORY_TO_EXCLUDE);
    }

}
