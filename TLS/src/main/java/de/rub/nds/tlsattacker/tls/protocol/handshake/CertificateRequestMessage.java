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

import de.rub.nds.tlsattacker.modifiablevariable.ModifiableVariableFactory;
import de.rub.nds.tlsattacker.modifiablevariable.ModifiableVariableProperty;
import de.rub.nds.tlsattacker.modifiablevariable.bytearray.ModifiableByteArray;
import de.rub.nds.tlsattacker.modifiablevariable.integer.ModifiableInteger;
import de.rub.nds.tlsattacker.tls.constants.ConnectionEnd;
import de.rub.nds.tlsattacker.tls.constants.ClientCertificateType;
import de.rub.nds.tlsattacker.tls.constants.HandshakeMessageType;
import de.rub.nds.tlsattacker.tls.constants.HashAlgorithm;
import de.rub.nds.tlsattacker.tls.constants.SignatureAlgorithm;
import de.rub.nds.tlsattacker.util.ArrayConverter;

/**
 * import java.util.LinkedList;
 * import java.util.List;
 * import javax.xml.bind.annotation.XmlAccessType;
 * import javax.xml.bind.annotation.XmlAccessorType;
 * import javax.xml.bind.annotation.XmlElement;
 * import javax.xml.bind.annotation.XmlElementWrapper;
 * import javax.xml.bind.annotation.XmlElements;
 * import javax.xml.bind.annotation.XmlRootElement;
 */

/**
 * @author Juraj Somorovsky <juraj.somorovsky@rub.de>
 * @author Philip Riese <philip.riese@rub.de>
 */

/**
 * @XmlRootElement
 * @XmlAccessorType(XmlAccessType.FIELD)
 */
public class CertificateRequestMessage extends HandshakeMessage {

    /**
     * List of supported Client Certificate Types
     * 
     * @XmlElementWrapper
     * @XmlElements(value = { @XmlElement(type = ClientCertificateType.class,
     *                    name = "ClientCertificateTypes") }) private
     *                    List<ClientCertificateType>
     *                    supportedClientCertificateTypes = new LinkedList<>();
     */

    @ModifiableVariableProperty(type = ModifiableVariableProperty.Type.COUNT)
    ModifiableInteger clientCertificateTypesCount;

    @ModifiableVariableProperty(type = ModifiableVariableProperty.Type.TLS_CONSTANT)
    ModifiableByteArray clientCertificateTypes;

    @ModifiableVariableProperty(type = ModifiableVariableProperty.Type.LENGTH)
    ModifiableInteger signatureHashAlgorithmsLength;

    @ModifiableVariableProperty(type = ModifiableVariableProperty.Type.TLS_CONSTANT)
    ModifiableByteArray signatureHashAlgorithms;

    @ModifiableVariableProperty(type = ModifiableVariableProperty.Type.LENGTH)
    ModifiableInteger distinguishedNamesLength;

    @ModifiableVariableProperty(type = ModifiableVariableProperty.Type.TLS_CONSTANT)
    ModifiableByteArray distinguishedNames;

    public CertificateRequestMessage() {
	super(HandshakeMessageType.CERTIFICATE_REQUEST);
	this.messageIssuer = ConnectionEnd.SERVER;
    }

    public CertificateRequestMessage(ConnectionEnd messageIssuer) {
	super(HandshakeMessageType.CERTIFICATE_REQUEST);
	this.messageIssuer = messageIssuer;
    }

    public ModifiableInteger getClientCertificateTypesCount() {
	return clientCertificateTypesCount;
    }

    public void setClientCertificateTypesCount(ModifiableInteger clientCertificateTypesCount) {
	this.clientCertificateTypesCount = clientCertificateTypesCount;
    }

    public void setClientCertificateTypesCount(int clientCertificateTypesCount) {
	this.clientCertificateTypesCount = ModifiableVariableFactory.safelySetValue(this.clientCertificateTypesCount,
		clientCertificateTypesCount);
    }

    public ModifiableByteArray getClientCertificateTypes() {
	return clientCertificateTypes;
    }

    public void setClientCertificateTypes(ModifiableByteArray clientCertificateTypes) {
	this.clientCertificateTypes = clientCertificateTypes;
    }

    public void setClientCertificateTypes(byte[] clientCertificateTypes) {
	this.clientCertificateTypes = ModifiableVariableFactory.safelySetValue(this.clientCertificateTypes,
		clientCertificateTypes);
    }

    public ModifiableInteger getSignatureHashAlgorithmsLength() {
	return signatureHashAlgorithmsLength;
    }

    public void setSignatureHashAlgorithmsLength(ModifiableInteger signatureHashAlgorithmsLength) {
	this.signatureHashAlgorithmsLength = signatureHashAlgorithmsLength;
    }

    public void setSignatureHashAlgorithmsLength(int signatureHashAlgorithmsLength) {
	this.signatureHashAlgorithmsLength = ModifiableVariableFactory.safelySetValue(
		this.signatureHashAlgorithmsLength, signatureHashAlgorithmsLength);
    }

    public ModifiableByteArray getSignatureHashAlgorithms() {
	return signatureHashAlgorithms;
    }

    public void setSignatureHashAlgorithms(ModifiableByteArray signatureHashAlgorithms) {
	this.signatureHashAlgorithms = signatureHashAlgorithms;
    }

    public void setSignatureHashAlgorithms(byte[] signatureHashAlgorithms) {
	this.signatureHashAlgorithms = ModifiableVariableFactory.safelySetValue(this.signatureHashAlgorithms,
		signatureHashAlgorithms);
    }

    public ModifiableInteger getDistinguishedNamesLength() {
	return distinguishedNamesLength;
    }

    public void setDistinguishedNamesLength(ModifiableInteger distinguishedNamesLength) {
	this.distinguishedNamesLength = distinguishedNamesLength;
    }

    public void setDistinguishedNamesLength(int distinguishedNamesLength) {
	this.distinguishedNamesLength = ModifiableVariableFactory.safelySetValue(this.distinguishedNamesLength,
		distinguishedNamesLength);
    }

    public ModifiableByteArray getDistinguishedNames() {
	return distinguishedNames;
    }

    public void setDistinguishedNames(ModifiableByteArray distinguishedNames) {
	this.distinguishedNames = distinguishedNames;
    }

    public void setDistinguishedNames(byte[] distinguishedNames) {
	this.distinguishedNames = ModifiableVariableFactory.safelySetValue(this.distinguishedNames, distinguishedNames);
    }

    /**
     * public void
     * setSupportedClientCertificateTypes(List<ClientCertificateType>
     * supportedClientCertificateTypes) { this.supportedClientCertificateTypes =
     * supportedClientCertificateTypes; }
     */

    /**
     * public List<ClientCertificateType> getSupportedClientCertificateTypes() {
     * return supportedClientCertificateTypes; }
     */

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder(super.toString());
	sb.append("\n  Certificate Types Count: ").append(clientCertificateTypesCount.getValue());
	sb.append("\n  Certificate Types: ");
	for (int i = 0; i < clientCertificateTypesCount.getValue(); i++) {
	    sb.append(ClientCertificateType.getClientCertificateType(clientCertificateTypes.getValue()[i]))
		    .append(", ");
	}
	sb.append("\n  Signature Hash Algorithms Length: ").append(signatureHashAlgorithmsLength.getValue());
	sb.append("\n  Signature Hash Algorithms: ");
	for (int i = 0; i < signatureHashAlgorithmsLength.getValue(); i = i + 2) {
	    sb.append(HashAlgorithm.getHashAlgorithm(signatureHashAlgorithms.getValue()[i])).append("-");
	    sb.append(SignatureAlgorithm.getSignatureAlgorithm(signatureHashAlgorithms.getValue()[i + 1])).append(", ");
	}
	sb.append("\n  Distinguished Names Length: ").append(distinguishedNamesLength.getValue());
	// sb.append("\n  Distinguished Names: ").append(ArrayConverter.bytesToHexString(distinguishedNames.getValue()));
	return sb.toString();
    }

}
