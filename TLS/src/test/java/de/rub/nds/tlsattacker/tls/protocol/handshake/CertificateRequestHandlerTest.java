/**
 * TLS-Attacker - A Modular Penetration Testing Framework for TLS.
 *
 * Copyright (C) 2015 Chair for Network and Data Security,
 *                    Ruhr University Bochum
 *                    (juraj.somorovsky@rub.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.rub.nds.tlsattacker.tls.protocol.handshake;

import de.rub.nds.tlsattacker.tls.protocol.handshake.CertificateRequestHandler;
import de.rub.nds.tlsattacker.tls.constants.ClientCertificateType;
import de.rub.nds.tlsattacker.tls.constants.HandshakeByteLength;
import de.rub.nds.tlsattacker.tls.constants.HandshakeMessageType;
import de.rub.nds.tlsattacker.tls.constants.HashAlgorithm;
import de.rub.nds.tlsattacker.tls.constants.SignatureAlgorithm;
import de.rub.nds.tlsattacker.tls.constants.SignatureAndHashAlgorithm;
import de.rub.nds.tlsattacker.tls.protocol.handshake.CertificateRequestMessage;
import de.rub.nds.tlsattacker.tls.workflow.TlsContext;
import de.rub.nds.tlsattacker.util.ArrayConverter;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * 
 * @author Juraj Somorovsky - juraj.somorovsky@rub.de
 * @author Florian Pfützenreuter - florian.pfuetzenreuter@rub.de
 * @author Philip Riese <philip.riese@rub.de>
 */
public class CertificateRequestHandlerTest {

    private CertificateRequestHandler handler;

    public CertificateRequestHandlerTest() {
	handler = new CertificateRequestHandler(new TlsContext());
    }

    /**
     * Test of prepareMessageAction method, of class CertificateRequestHandler.
     */
    @Test
    public void testPrepareMessageAction() {
	handler.setProtocolMessage(new CertificateRequestMessage());

	CertificateRequestMessage message = (CertificateRequestMessage) handler.getProtocolMessage();

	byte[] returned = handler.prepareMessageAction();
	byte[] expected = ArrayConverter.concatenate(
		new byte[] { HandshakeMessageType.CERTIFICATE_REQUEST.getValue() }, new byte[] { 0x00, 0x00, 0x12 },
		new byte[] { 0x01 }, message.getClientCertificateTypes().getValue(), new byte[] { 0x00, 0x0C }, message
			.getSignatureHashAlgorithms().getValue(), new byte[] { 0x00, 0x00 });

	assertNotNull("Confirm function didn't return 'NULL'", returned);
	assertArrayEquals("Confirm returned message equals the expected message", expected, returned);

    }

    /**
     * Test of parseMessageAction method, of class CertificateRequestHandler.
     */
    @Test
    public void testParseMessageAction() {
	handler.initializeProtocolMessage();

	byte[] inputBytes = { HandshakeMessageType.CERTIFICATE_REQUEST.getValue(), 0x00, 0x00, 0x07, 0x01,
		ClientCertificateType.RSA_SIGN.getValue(), 0x00, 0x02 };
	byte[] sigHashAlg = new SignatureAndHashAlgorithm(SignatureAlgorithm.RSA, HashAlgorithm.SHA512).getByteValue();
	inputBytes = ArrayConverter.concatenate(inputBytes, sigHashAlg, new byte[] { 0x00, 0x00 });
	int endPointer = handler.parseMessageAction(inputBytes, 0);
	CertificateRequestMessage message = (CertificateRequestMessage) handler.getProtocolMessage();

	assertNotNull("Confirm endPointer is not 'NULL'", endPointer);
	assertEquals("Confirm actual message length", endPointer, 12);
	assertEquals("Confirm message type", HandshakeMessageType.CERTIFICATE_REQUEST,
		message.getHandshakeMessageType());
	assertTrue("Confirm certificate type count", message.getClientCertificateTypesCount().getValue() == 1);
	assertEquals("Confirm certificate type", ClientCertificateType.RSA_SIGN.getValue(), message
		.getClientCertificateTypes().getValue()[0]);
	assertTrue("Confirm SignatureAndHashAlgorithm count",
		message.getSignatureHashAlgorithmsLength().getValue() == 2);
	assertArrayEquals("Confirm SignatureAndHashAlgorithm type", sigHashAlg, message.getSignatureHashAlgorithms()
		.getValue());
	assertTrue("Confirm DistinguishedName is empty", message.getDistinguishedNamesLength().getValue() == 0);
    }

}
