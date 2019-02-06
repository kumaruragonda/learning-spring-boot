package com.xyz.sample.one;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.ConnectException;

import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.core.NestedCheckedException;

import com.xyz.sample.SampleApplication;

public class LiquibaseRunTest {

    @Rule
	public OutputCapture outputCapture = new OutputCapture();
    
    @Test
	public void testDefaultSettings() throws Exception {

		try {
		    System.setProperty("spring.profiles.default", "dev");
			SampleApplication.main(new String[] { "--server.port=0" });
		}
		catch (IllegalStateException ex) {
			if (serverNotRunning(ex)) {
				return;
			}
		}
		String output = this.outputCapture.toString();
		
		assertThat(output).contains("Successfully acquired change log lock")
				.contains("Creating database history "
						+ "table with name: PUBLIC.DATABASECHANGELOG")
				.contains("Table TEST created")
				.contains("ChangeSet changelogs/201902040702-create_test_table.xml::create test table::test ran successfully")
				.contains("Successfully released change log lock");
	}

	@SuppressWarnings("serial")
	private boolean serverNotRunning(IllegalStateException ex) {
		NestedCheckedException nested = new NestedCheckedException("failed", ex) {
		};
		if (nested.contains(ConnectException.class)) {
			Throwable root = nested.getRootCause();
			if (root.getMessage().contains("Connection refused")) {
				return true;
			}
		}
		return false;
	}
}
