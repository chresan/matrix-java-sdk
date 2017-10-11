/*
 * matrix-java-sdk - Matrix Client SDK for Java
 * Copyright (C) 2017 Arne Augenstein
 *
 * https://max.kamax.io/
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package io.kamax.matrix.client.regular;

import io.kamax.matrix.client.*;

import org.junit.Test;

import java.net.URISyntaxException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MatrixHttpClientTest extends MatrixHttpTest {
    private String setDisplaynameUrl = String.format("/_matrix/client/r0/profile/%s/displayname",
            createClientContext().getUser().getId()) + tokenParameter;
    private String displayName = "display name";

    public MatrixHttpClientTest() throws URISyntaxException {
    }

    @Test
    public void setDisplayName() throws URISyntaxException {
        stubFor(put(urlEqualTo(setDisplaynameUrl)).willReturn(aResponse().withStatus(200)));
        createClientObject().setDisplayName(displayName);
    }

    @Test
    public void setDisplayNameError429() throws URISyntaxException {
        stubFor(put(urlEqualTo(setDisplaynameUrl)).willReturn(aResponse().withStatus(429).withBody(error429Response)));

        MatrixClientRequestException e = assertThrows(MatrixClientRequestException.class,
                () -> createClientObject().setDisplayName(displayName));
        checkErrorInfo429(e);
    }

    private MatrixHttpClient createClientObject() throws URISyntaxException {
        MatrixClientContext context = createClientContext();
        return new MatrixHttpClient(context);
    }
}