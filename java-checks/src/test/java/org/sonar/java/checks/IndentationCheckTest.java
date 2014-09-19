/*
 * SonarQube Java
 * Copyright (C) 2012 SonarSource
 * dev@sonar.codehaus.org
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.sonar.java.checks;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.sonar.java.JavaAstScanner;
import org.sonar.squidbridge.api.SourceFile;
import org.sonar.squidbridge.checks.CheckMessagesVerifier;
import org.sonar.squidbridge.checks.CheckMessagesVerifierRule;

import java.io.File;

public class IndentationCheckTest {

  @Rule
  public CheckMessagesVerifierRule checkMessagesVerifier = new CheckMessagesVerifierRule();

  @Test
  @Ignore("FIXME")
  public void detected() {
    SourceFile file = JavaAstScanner.scanSingleFile(new File("src/test/files/checks/IndentationCheck.java"), new IndentationCheck());
    checkMessagesVerifier.verify(file.getCheckMessages())
      .next().atLine(3).withMessage("Make this line start at column 3.")
      .next().atLine(10)
      .next().atLine(11)
      .next().atLine(16)
      .next().atLine(21)
      .next().atLine(24).withMessage("Make this line start at column 9.")
      .next().atLine(35)
      .next().atLine(48)
      .next().atLine(54)
      .next().atLine(79)
      .next().atLine(102);
  }

  @Test
  @Ignore("FIXME")
  public void custom() {
    IndentationCheck check = new IndentationCheck();
    check.indentationLevel = 4;

    SourceFile file = JavaAstScanner.scanSingleFile(new File("src/test/files/checks/IndentationCheck.java"), check);
    CheckMessagesVerifier.verify(file.getCheckMessages())
      .next().atLine(2).withMessage("Make this line start at column 5.")
      .next().atLine(7)
      .next().atLine(10);
  }

}
