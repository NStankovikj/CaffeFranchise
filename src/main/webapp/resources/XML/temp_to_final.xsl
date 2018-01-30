<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" indent="yes"/>
    <xsl:template match="/">
        <MCCI_IN200100UV01 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="urn:hl7-org:v3" ITSVersion="XML_1.0" xsi:schemaLocation="urn:hl7-org:v3 MCCI_IN200100UV01.xsd" >
            <id extension="{report/N12}" root="2.16.840.1.113883.3.989.2.1.3.22"/>
            <!-- N.1.2: Batch Number -->
            <creationTime value="{report/N15}"/>
            <!-- N.1.5: Date of Batch Transmission -->
            <responseModeCode code="D"/>
            <interactionId extension="MCCI_IN200100UV01" root="2.16.840.1.113883.1.6"/>
            <name code="{report/N11}" codeSystem="2.16.840.1.113883.3.989.2.1.1.1" codeSystemVersion="1.01"/>
            <!-- N.1.1: Type of Messages in Batch -->
            <xsl:for-each select="report/message">
                <PORR_IN049016UV>
                    <id extension="{N2r1}" root="2.16.840.1.113883.3.989.2.1.3.1"/>
                    <!-- N.2.r.1: Message Identifier -->
                    <creationTime value="{N2r4}"/>
                    <!-- N.2.r.4: Date of Message Creation -->
                    <interactionId extension="PORR_IN049016UV" root="2.16.840.1.113883.1.6"/>
                    <processingCode code="P"/>
                    <processingModeCode code="T"/>
                    <acceptAckCode code="AL"/>
                    <receiver typeCode="RCV">
                        <device classCode="DEV" determinerCode="INSTANCE">
                            <id extension="{N2r3}" root="2.16.840.1.113883.3.989.2.1.3.12"/>
                            <!-- N.2.r.3: Message Receiver Identifier -->
                        </device>
                    </receiver>
                    <sender typeCode="SND">
                        <device classCode="DEV" determinerCode="INSTANCE">
                            <id extension="{N2r2}" root="2.16.840.1.113883.3.989.2.1.3.11"/>
                            <!-- N.2.r.2: Message Sender Identifier -->
                        </device>
                    </sender>
                    <controlActProcess classCode="CACT" moodCode="EVN">
                        <code code="PORR_TE049016UV" codeSystem="2.16.840.1.113883.1.18"/>
                        <!-- HL7 Trigger Event ID -->
                        <effectiveTime value="{C12}"/>
                        <!-- C.1.2: Date of Creation -->
                        <subject typeCode="SUBJ">
                            <investigationEvent classCode="INVSTG" moodCode="EVN">
                                <id extension="{C11}" root="2.16.840.1.113883.3.989.2.1.3.1"/>
                                <!-- C.1.1: Sender's (case) Safety Report Unique Identifier -->
                                <id extension="{C181}" root="2.16.840.1.113883.3.989.2.1.3.2"/>
                                <!-- C.1.8.1: Worldwide Unique Case Identification Number -->
                                <code code="PAT_ADV_EVNT" codeSystem="2.16.840.1.113883.5.4"/>
                                <text>
                                    <xsl:value-of select="H1"/>
                                </text>
                                <!-- H.1: Case Narrative Including Clinical Course, Therapeutic Measures, Outcome and Additional Relevant Information -->
                                <statusCode code="active"/>
                                <effectiveTime>
                                    <low value="{C14}"/>
                                    <!-- C.1.4: Date Report Was First Received from Source -->
                                </effectiveTime>
                                <availabilityTime value="{C15}"/>
                                <!-- C.1.5: Date of Most Recent Information for This Report -->
                                <xsl:for-each select="C161r">
                                    <reference typeCode="REFR">
                                        <document classCode="DOC" moodCode="EVN">
                                            <code code="1" codeSystem="2.16.840.1.113883.3.989.2.1.1.27" codeSystemVersion="1.0" displayName="documentsHeldBySender"/>
                                            <xsl:if test="C161r1">
                                                <title>
                                                    <xsl:value-of select="C161r1"/>
                                                </title>
                                                <!-- C.1.6.1.r.1: Documents Held by Sender (repeat as necessary) #1 -->
                                            </xsl:if>
                                            <xsl:if test="C161r2">
                                                <text mediaType="application/pdf" representation="B64">
                                                    <xsl:value-of select="C161r2"/>
                                                </text>
                                                <!-- C.1.6.1.r.2: Included Documents #1 -->
                                            </xsl:if>
                                        </document>
                                    </reference>
                                </xsl:for-each>
                                <xsl:for-each select="C4r">
                                    <reference typeCode="REFR">
                                        <document classCode="DOC" moodCode="EVN">
                                            <code code="2" codeSystem="2.16.840.1.113883.3.989.2.1.1.27" codeSystemVersion="1.0" displayName="literatureReference"/>
                                            <xsl:if test="C4r2">
                                                <text mediaType="application/pdf" representation="B64">
                                                    <xsl:value-of select="C4r2"/>
                                                </text>
                                                <!-- C.4.r.2: Included Documents #1 -->
                                            </xsl:if>
                                            <xsl:if test="C4r1">
                                                <xsl:choose>
                                                    <xsl:when test="C4r1[@nf]">
                                                        <bibliographicDesignationText nullFlavor="{C4r1/@nf}"/>
                                                    </xsl:when>
                                                    <xsl:otherwise>
                                                        <bibliographicDesignationText>
                                                            <xsl:value-of select="C4r1"/>
                                                        </bibliographicDesignationText>
                                                    </xsl:otherwise>
                                                    <!-- C.4.r.1: Literature Reference(s) #1 -->
                                                </xsl:choose>
                                            </xsl:if>
                                        </document>
                                    </reference>
                                </xsl:for-each>
                                <component typeCode="COMP">
                                    <adverseEventAssessment classCode="INVSTG" moodCode="EVN">
                                        <subject1 typeCode="SBJ">
                                            <primaryRole classCode="INVSBJ">
                                                <player1 classCode="PSN" determinerCode="INSTANCE">
                                                    <xsl:choose>
                                                        <xsl:when test="D1[@nf]">
                                                            <name nullFlavor="{D1/@nf}"/>
                                                        </xsl:when>
                                                        <xsl:otherwise>
                                                            <name>
                                                                <xsl:value-of select="D1"/>
                                                            </name>
                                                        </xsl:otherwise>
                                                    </xsl:choose>
                                                    <!-- D.1: Patient (name or initials) -->
                                                    <xsl:if test="D5">
                                                        <xsl:choose>
                                                            <xsl:when test="D5[@nf]">
                                                                <administrativeGenderCode nullFlavor="{D5/@nf}" codeSystem="1.0.5218"/>
                                                            </xsl:when>
                                                            <xsl:otherwise>
                                                                <administrativeGenderCode code="{D5}" codeSystem="1.0.5218"/>
                                                            </xsl:otherwise>
                                                        </xsl:choose>
                                                        <!-- D.5 Sex -->
                                                    </xsl:if>
                                                    <xsl:if test="D21">
                                                        <xsl:choose>
                                                            <xsl:when test="D21[@nf]">
                                                                <birthTime nullFlavor="{D21/@nf}"/>
                                                            </xsl:when>
                                                            <xsl:otherwise>
                                                                <birthTime value="{D21}"/>
                                                            </xsl:otherwise>
                                                        </xsl:choose>
                                                        <!-- D.2.1: Date of Birth -->
                                                    </xsl:if>
                                                    <xsl:if test="D91">
                                                        <xsl:choose>
                                                            <xsl:when test="D91[@nf]">
                                                                <deceasedTime nullFlavor="{D91/@nf}"/>
                                                            </xsl:when>
                                                            <xsl:otherwise>
                                                                <deceasedTime value="{D91}"/>
                                                            </xsl:otherwise>
                                                        </xsl:choose>
                                                        <!-- D.9.1: Date of Death -->
                                                    </xsl:if>
                                                    <xsl:if test="D111">
                                                        <asIdentifiedEntity classCode="IDENT">
                                                            <xsl:choose>
                                                                <xsl:when test="D111[@nf]">
                                                                    <id nullFlavor="{D111/@nf}" root="2.16.840.1.113883.3.989.2.1.3.7"/>
                                                                </xsl:when>
                                                                <xsl:otherwise>
                                                                    <id extension="{D111}" root="2.16.840.1.113883.3.989.2.1.3.7"/>
                                                                </xsl:otherwise>
                                                            </xsl:choose>
                                                            <!-- D.1.1.1: Patient Medical Record Number(s) and Source(s) of the Record Number (GP Medical Record Number) -->
                                                            <code code="1" codeSystem="2.16.840.1.113883.3.989.2.1.1.4" codeSystemVersion="1.0" displayName="GP"/>
                                                        </asIdentifiedEntity>
                                                    </xsl:if>
                                                    <xsl:if test="D112">
                                                        <asIdentifiedEntity classCode="IDENT">
                                                            <xsl:choose>
                                                                <xsl:when test="D112[@nf]">
                                                                    <id nullFlavor="{D112/@nf}" root="2.16.840.1.113883.3.989.2.1.3.8"/>
                                                                </xsl:when>
                                                                <xsl:otherwise>
                                                                    <id extension="{D112}" root="2.16.840.1.113883.3.989.2.1.3.8"/>
                                                                </xsl:otherwise>
                                                            </xsl:choose>
                                                            <!-- D.1.1.2: Patient Medical Record Number(s) and Source(s) of the Record Number (Specialist Record Number) -->
                                                            <code code="2" codeSystem="2.16.840.1.113883.3.989.2.1.1.4" codeSystemVersion="1.0" displayName="Specialist"/>
                                                        </asIdentifiedEntity>
                                                    </xsl:if>
                                                    <xsl:if test="D113">
                                                        <asIdentifiedEntity classCode="IDENT">
                                                            <xsl:choose>
                                                                <xsl:when test="D113[@nf]">
                                                                    <id nullFlavor="{D113/@nf}" root="2.16.840.1.113883.3.989.2.1.3.9"/>
                                                                </xsl:when>
                                                                <xsl:otherwise>
                                                                    <id extension="{D113}" root="2.16.840.1.113883.3.989.2.1.3.9"/>
                                                                </xsl:otherwise>
                                                            </xsl:choose>
                                                            <!-- D.1.1.3: Patient Medical Record Number(s) and Source(s) of the Record Number (Hospital Record Number) -->
                                                            <code code="3" codeSystem="2.16.840.1.113883.3.989.2.1.1.4" codeSystemVersion="1.0" displayName="Hospital Record"/>
                                                        </asIdentifiedEntity>
                                                    </xsl:if>
                                                    <xsl:if test="D114">
                                                        <asIdentifiedEntity classCode="IDENT">
                                                            <xsl:choose>
                                                                <xsl:when test="D114[@nf]">
                                                                    <id nullFlavor="{D114/@nf}" root="2.16.840.1.113883.3.989.2.1.3.10"/>
                                                                </xsl:when>
                                                                <xsl:otherwise>
                                                                    <id extension="{D114}" root="2.16.840.1.113883.3.989.2.1.3.10"/>
                                                                </xsl:otherwise>
                                                            </xsl:choose>
                                                            <!-- D.1.1.4: Patient Medical Record Number(s) and Source(s) of the Record Number (Investigation Number) -->
                                                            <code code="4" codeSystem="2.16.840.1.113883.3.989.2.1.1.4" codeSystemVersion="1.0" displayName="Investigation"/>
                                                        </asIdentifiedEntity>
                                                    </xsl:if>
                                                    <xsl:if test="D101 or D106 or D1021 or D1022a or D1022b or D103 or D104 or D105 or D1071r or D1072 or D108r">
                                                        <role classCode="PRS">
                                                            <code code="PRN" codeSystem="2.16.840.1.113883.5.111"/>
                                                            <xsl:if test="D101 or D1021 or D106">
                                                                <associatedPerson classCode="PSN" determinerCode="INSTANCE">
                                                                    <xsl:if test="D101">
                                                                        <xsl:choose>
                                                                            <xsl:when test="D101[@nf]">
                                                                                <name nullFlavor="{D101/@nf}"/>
                                                                            </xsl:when>
                                                                            <xsl:otherwise>
                                                                                <name>
                                                                                    <xsl:value-of select="D101"/>
                                                                                </name>
                                                                            </xsl:otherwise>
                                                                        </xsl:choose>
                                                                        <!-- D.10.1: Parent Identification -->
                                                                    </xsl:if>
                                                                    <xsl:if test="D106">
                                                                        <xsl:choose>
                                                                            <xsl:when test="D106[@nf]">
                                                                                <administrativeGenderCode nullFlavor="{D106/@nf}" codeSystem="1.0.5218"/>
                                                                            </xsl:when>
                                                                            <xsl:otherwise>
                                                                                <administrativeGenderCode code="{D106}" codeSystem="1.0.5218"/>
                                                                            </xsl:otherwise>
                                                                        </xsl:choose>
                                                                        <!-- D.10.6: Sex of Parent -->
                                                                    </xsl:if>
                                                                    <xsl:if test="D1021">
                                                                        <xsl:choose>
                                                                            <xsl:when test="D1021[@nf]">
                                                                                <birthTime nullFlavor="{D1021/@nf}"/>
                                                                            </xsl:when>
                                                                            <xsl:otherwise>
                                                                                <birthTime value="{D1021}"/>
                                                                            </xsl:otherwise>
                                                                        </xsl:choose>
                                                                        <!-- D.10.2.1: Date of Birth of Parent -->
                                                                    </xsl:if>
                                                                </associatedPerson>
                                                            </xsl:if>
                                                            <xsl:if test="D1022a or D1022b">
                                                                <subjectOf2 typeCode="SBJ">
                                                                    <observation classCode="OBS" moodCode="EVN">
                                                                        <code code="3" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="age"/>
                                                                        <value xsi:type="PQ" value="{D1022a}" unit="{D1022b}"/>
                                                                        <!-- D.10.2.2a: Age of Parent (number) -->
                                                                        <!-- D.10.2.2b: Age of Parent (unit) -->
                                                                    </observation>
                                                                </subjectOf2>
                                                            </xsl:if>
                                                            <xsl:if test="D103">
                                                                <subjectOf2 typeCode="SBJ">
                                                                    <observation classCode="OBS" moodCode="EVN">
                                                                        <code code="22" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="lastMenstrualPeriodDate"/>
                                                                        <xsl:choose>
                                                                            <xsl:when test="D103[@nf]">
                                                                                <value xsi:type="TS" nullFlavor="{D103/@nf}"/>
                                                                            </xsl:when>
                                                                            <xsl:otherwise>
                                                                                <value xsi:type="TS" value="{D103}"/>
                                                                            </xsl:otherwise>
                                                                        </xsl:choose>
                                                                        <!-- D.10.3: Last Menstrual Period Date of Parent -->
                                                                    </observation>
                                                                </subjectOf2>
                                                            </xsl:if>
                                                            <xsl:if test="D104">
                                                                <subjectOf2 typeCode="SBJ">
                                                                    <observation classCode="OBS" moodCode="EVN">
                                                                        <code code="7" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="bodyWeight"/>
                                                                        <value xsi:type="PQ" value="{D104}" unit="kg"/>
                                                                        <!-- D.10.4: Body Weight (kg) of Parent -->
                                                                    </observation>
                                                                </subjectOf2>
                                                            </xsl:if>
                                                            <xsl:if test="D105">
                                                                <subjectOf2 typeCode="SBJ">
                                                                    <observation classCode="OBS" moodCode="EVN">
                                                                        <code code="17" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="height"/>
                                                                        <value xsi:type="PQ" value="{D105}" unit="cm"/>
                                                                        <!-- D.10.5: Height (cm) of Parent -->
                                                                    </observation>
                                                                </subjectOf2>
                                                            </xsl:if>
                                                            <subjectOf2 typeCode="SBJ">
                                                                <organizer classCode="CATEGORY" moodCode="EVN">
                                                                    <code code="1" codeSystem="2.16.840.1.113883.3.989.2.1.1.20" codeSystemVersion="1.0" displayName="relevantMedicalHistoryAndConcurrentConditions"/>
                                                                    <xsl:for-each select="D1071r">
                                                                        <component typeCode="COMP">
                                                                            <observation classCode="OBS" moodCode="EVN">
                                                                                <xsl:if test= "D1071r1a or D1071r1b">
                                                                                    <code code="{D1071r1b}" codeSystem="2.16.840.1.113883.6.163" codeSystemVersion="{D1071r1a}"/>
                                                                                    <!-- D.10.7.1.r.1a: MedDRA Version for Medical History #1 -->
                                                                                    <!-- D.10.7.1.r.1b: Medical History (disease / surgical procedure/ etc.) (MedDRA code) #1 -->
                                                                                </xsl:if>
                                                                                <xsl:if test="D1071r2 or D1071r4">
                                                                                    <effectiveTime xsi:type="IVL_TS">
                                                                                        <xsl:if test="D1071r2">
                                                                                            <low value="{D1071r2}"/>
                                                                                            <!-- D.10.7.1.r.2: Start Date #1 -->
                                                                                        </xsl:if>
                                                                                        <xsl:if test = "D1071r4">
                                                                                            <high value="{D1071r4}"/>
                                                                                            <!-- D.10.7.1.r.4: End Date #1 -->
                                                                                        </xsl:if>
                                                                                    </effectiveTime>
                                                                                </xsl:if>
                                                                                <xsl:if test="D1071r5">
                                                                                    <outboundRelationship2 typeCode="COMP">
                                                                                        <observation classCode="OBS" moodCode="EVN">
                                                                                            <code code="10" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="comment"/>
                                                                                            <value xsi:type="ED">
                                                                                                <xsl:value-of select="D1071r5"/>
                                                                                            </value>
                                                                                            <!-- D.10.7.1.r.5: Comments #1 -->
                                                                                        </observation>
                                                                                    </outboundRelationship2>
                                                                                </xsl:if>
                                                                                <xsl:if test="D1071r3">
                                                                                    <inboundRelationship typeCode="REFR">
                                                                                        <observation classCode="OBS" moodCode="EVN">
                                                                                            <code code="13" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="continuing"/>
                                                                                            <xsl:choose>
                                                                                                <xsl:when test="D1071r3[@nf]">
                                                                                                    <value xsi:type="BL" nullFlavor="{D1071r3/@nf}"/>
                                                                                                </xsl:when>
                                                                                                <xsl:otherwise>
                                                                                                    <value xsi:type="BL" value="{D1071r3}"/>
                                                                                                </xsl:otherwise>
                                                                                            </xsl:choose>
                                                                                            <!-- D.10.7.1.r.3: Continuing #1 -->
                                                                                        </observation>
                                                                                    </inboundRelationship>
                                                                                </xsl:if>
                                                                            </observation>
                                                                        </component>
                                                                    </xsl:for-each>
                                                                    <xsl:if test="D1072">
                                                                        <component typeCode="COMP">
                                                                            <observation classCode="OBS" moodCode="EVN">
                                                                                <code code="18" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="historyAndConcurrentConditionText"/>
                                                                                <value xsi:type="ED">
                                                                                    <xsl:value-of select="D1072"/>
                                                                                </value>
                                                                                <!-- D.10.7.2: Text for Relevant Medical History and Concurrent Conditions of Parent -->
                                                                            </observation>
                                                                        </component>
                                                                    </xsl:if>
                                                                </organizer>
                                                            </subjectOf2>
                                                            <xsl:if test="D108r">
                                                                <subjectOf2 typeCode="SBJ">
                                                                    <organizer classCode="CATEGORY" moodCode="EVN">
                                                                        <code code="2" codeSystem="2.16.840.1.113883.3.989.2.1.1.20" codeSystemVersion="1.0" displayName="drugHistory"/>
                                                                        <xsl:for-each select="D108r">
                                                                            <component typeCode="COMP">
                                                                                <substanceAdministration classCode="SBADM" moodCode="EVN">
                                                                                    <xsl:if test="D108r4 or D108r5">
                                                                                        <effectiveTime xsi:type="IVL_TS">
                                                                                            <xsl:if test="D108r4">
                                                                                                <xsl:choose>
                                                                                                    <xsl:when test="D108r4[@nf]">
                                                                                                        <low nullFlavor="{D108r4/@nf}"/>
                                                                                                    </xsl:when>
                                                                                                    <xsl:otherwise>
                                                                                                        <low value="{D108r4}"/>
                                                                                                    </xsl:otherwise>
                                                                                                </xsl:choose>
                                                                                                <!-- D.10.8.r.4: Start Date #1 -->
                                                                                            </xsl:if>
                                                                                            <xsl:if test="D108r5">
                                                                                                <xsl:choose>
                                                                                                    <xsl:when test="D108r5[@nf]">
                                                                                                        <high nullFlavor="{D108r5/@nf}"/>
                                                                                                    </xsl:when>
                                                                                                    <xsl:otherwise>
                                                                                                        <high value="{D108r5}"/>
                                                                                                    </xsl:otherwise>
                                                                                                </xsl:choose>
                                                                                                <!-- D.10.8.r.5: End Date #1 -->
                                                                                            </xsl:if>
                                                                                        </effectiveTime>
                                                                                    </xsl:if>
                                                                                    <xsl:if test="D108r1 or D108r2a or D108r2b or D108r3a or D108r3b">
                                                                                        <consumable typeCode="CSM">
                                                                                            <instanceOfKind classCode="INST">
                                                                                                <kindOfProduct classCode="MMAT" determinerCode="KIND">
                                                                                                    <xsl:if test="D108r2a or D108r2b">
                                                                                                        <xsl:element name="code">
                                                                                                            <xsl:attribute name="codeSystem">TBD-MPID</xsl:attribute>
                                                                                                            <xsl:if test="D108r2a">
                                                                                                                <xsl:attribute name="codeSystemVersion">
                                                                                                                    <xsl:value-of select="D108r2a"/>
                                                                                                                </xsl:attribute>
                                                                                                            </xsl:if>
                                                                                                            <xsl:if test="D108r2b">
                                                                                                                <xsl:attribute name="code">
                                                                                                                    <xsl:value-of select="D108r2b"/>
                                                                                                                </xsl:attribute>
                                                                                                            </xsl:if>
                                                                                                        </xsl:element>
                                                                                                        <!-- D.10.8.r.2a: MPID Version Date / Number #1 -->
                                                                                                        <!-- D.10.8.r.2b: Medicinal Product Identifier (MPID) #1 -->
                                                                                                    </xsl:if>
                                                                                                    <xsl:if test="D108r3a or D108r3b">
                                                                                                        <xsl:element name="code">
                                                                                                            <xsl:attribute name="codeSystem">TBD-PhPID</xsl:attribute>
                                                                                                            <xsl:if test="D108r3a">
                                                                                                                <xsl:attribute name="codeSystemVersion">
                                                                                                                    <xsl:value-of select="D108r3a"/>
                                                                                                                </xsl:attribute>
                                                                                                            </xsl:if>
                                                                                                            <xsl:if test="D108r3b">
                                                                                                                <xsl:attribute name="code">
                                                                                                                    <xsl:value-of select="D108r3b"/>
                                                                                                                </xsl:attribute>
                                                                                                            </xsl:if>
                                                                                                        </xsl:element>
                                                                                                        <!-- D.10.8.r.3a: PhPID Version Date / Number #2 -->
                                                                                                        <!-- D.10.8.r.3b: Pharmaceutical Product Identifier (PhPID) #2 -->
                                                                                                    </xsl:if>
                                                                                                    <xsl:if test="D108r1">
                                                                                                        <name>
                                                                                                            <xsl:value-of select="D108r1"/>
                                                                                                        </name>
                                                                                                        <!-- D.10.8.r.1: Name of Drug as Reported #1 -->
                                                                                                    </xsl:if>
                                                                                                </kindOfProduct>
                                                                                            </instanceOfKind>
                                                                                        </consumable>
                                                                                    </xsl:if>
                                                                                    <xsl:if test="D108r6a or D108r6b">
                                                                                        <outboundRelationship2 typeCode="RSON">
                                                                                            <observation classCode="OBS" moodCode="EVN">
                                                                                                <code code="19" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="indication"/>
                                                                                                <value xsi:type="CE" code="{D108r6b}" codeSystem="2.16.840.1.113883.6.163" codeSystemVersion="{D108r6a}"/>
                                                                                                <!-- D.10.8.r.6a: MedDRA Version for Indication #1 -->
                                                                                                <!-- D.10.8.r.6b: Indication (MedDRA code) #1 -->
                                                                                            </observation>
                                                                                        </outboundRelationship2>
                                                                                    </xsl:if>
                                                                                    <xsl:if test="D108r7a or D108r7b">
                                                                                        <outboundRelationship2 typeCode="CAUS">
                                                                                            <observation classCode="OBS" moodCode="EVN">
                                                                                                <code code="29" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="reaction"/>
                                                                                                <value xsi:type="CE" code="{D108r7b}" codeSystem="2.16.840.1.113883.6.163" codeSystemVersion="{D108r7a}"/>
                                                                                                <!-- D.10.8.r.7a: MedDRA Version for Reaction #1 -->
                                                                                                <!-- D.10.8.r.7b: Reactions (MedDRA code) #1 -->
                                                                                            </observation>
                                                                                        </outboundRelationship2>
                                                                                    </xsl:if>
                                                                                </substanceAdministration>
                                                                            </component>
                                                                        </xsl:for-each>
                                                                    </organizer>
                                                                </subjectOf2>
                                                            </xsl:if>
                                                        </role>
                                                    </xsl:if>
                                                </player1>
                                                <xsl:if test="C51r1 or C51r2 or C52 or C53 or C54">
                                                    <subjectOf1 typeCode="SBJ">
                                                        <researchStudy classCode="CLNTRL" moodCode="EVN">
                                                            <xsl:if test="C53">
                                                                <xsl:choose>
                                                                    <xsl:when test="C53[@nf]">
                                                                        <id nullFlavor="{C53/@nf}" root="2.16.840.1.113883.3.989.2.1.3.5"/>
                                                                    </xsl:when>
                                                                    <xsl:otherwise>
                                                                        <id extension="{C53}" root="2.16.840.1.113883.3.989.2.1.3.5"/>
                                                                    </xsl:otherwise>
                                                                </xsl:choose>
                                                                <!-- C.5.3: Sponsor Study Number -->
                                                            </xsl:if>
                                                            <xsl:if test="C54">
                                                                <code code="{C54}" codeSystem="2.16.840.1.113883.3.989.2.1.1.8" codeSystemVersion="1.0"/>
                                                                <!-- C.5.4: Study Type Where Reaction(s) / Event(s) Were Observed -->
                                                            </xsl:if>
                                                            <xsl:if test="C52">
                                                                <xsl:choose>
                                                                    <xsl:when test="C52[@nf]">
                                                                        <title nullFlavor="{C52/@nf}"/>
                                                                    </xsl:when>
                                                                    <xsl:otherwise>
                                                                        <title>
                                                                            <xsl:value-of select="C52"/>
                                                                        </title>
                                                                    </xsl:otherwise>
                                                                </xsl:choose>
                                                                <!-- C.5.2: Study Name -->
                                                            </xsl:if>
                                                            <xsl:for-each select="C51r">
                                                                <authorization typeCode="AUTH">
                                                                    <studyRegistration classCode="ACT" moodCode="EVN">
                                                                        <xsl:if test="C51r1">
                                                                            <xsl:choose>
                                                                                <xsl:when test="C51r1[@nf]">
                                                                                    <id nullFlavor="{C51r1/@nf}" root="2.16.840.1.113883.3.989.2.1.3.6"/>
                                                                                </xsl:when>
                                                                                <xsl:otherwise>
                                                                                    <id extension="{C51r1}" root="2.16.840.1.113883.3.989.2.1.3.6"/>
                                                                                </xsl:otherwise>
                                                                            </xsl:choose>
                                                                            <!-- C.5.1.r.1: Study Registration Number #1 -->
                                                                        </xsl:if>
                                                                        <xsl:if test="C51r2">
                                                                            <author typeCode="AUT">
                                                                                <territorialAuthority classCode="TERR">
                                                                                    <governingPlace classCode="COUNTRY" determinerCode="INSTANCE">
                                                                                        <xsl:choose>
                                                                                            <xsl:when test="C51r2[@nf]">
                                                                                                <code nullFlavor="{C51r2/@nf}" codeSystem="1.0.3166.1.2.2"/>
                                                                                            </xsl:when>
                                                                                            <xsl:otherwise>
                                                                                                <code code="{C51r2}" codeSystem="1.0.3166.1.2.2"/>
                                                                                            </xsl:otherwise>
                                                                                        </xsl:choose>
                                                                                        <!-- C.5.1.r.2: Study Registration Country #1 -->
                                                                                    </governingPlace>
                                                                                </territorialAuthority>
                                                                            </author>
                                                                        </xsl:if>
                                                                    </studyRegistration>
                                                                </authorization>
                                                            </xsl:for-each>
                                                        </researchStudy>
                                                    </subjectOf1>
                                                </xsl:if>
                                                <xsl:if test="D22a or D22b">
                                                    <subjectOf2 typeCode="SBJ">
                                                        <observation classCode="OBS" moodCode="EVN">
                                                            <code code="3" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="age"/>
                                                            <value xsi:type="PQ" value="{D22a}" unit="{D22b}"/>
                                                            <!-- D.2.2a: Age at Time of Onset of Reaction / Event (number) -->
                                                            <!-- D.2.2b: Age at Time of Onset of Reaction / Event (unit) -->
                                                        </observation>
                                                    </subjectOf2>
                                                </xsl:if>
                                                <xsl:if test="D221a or D221b">
                                                    <subjectOf2 typeCode="SBJ">
                                                        <observation classCode="OBS" moodCode="EVN">
                                                            <code code="16" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="gestationPeriod"/>
                                                            <value xsi:type="PQ" value="{D221a}" unit="{D221b}"/>
                                                            <!-- D.2.2.1a: Gestation Period When Reaction / Event Was Observed in the Foetus (number) -->
                                                            <!-- D.2.2.1b: Gestation Period When Reaction / Event Was Observed in the Foetus (unit) -->
                                                        </observation>
                                                    </subjectOf2>
                                                </xsl:if>
                                                <xsl:if test="D23">
                                                    <subjectOf2 typeCode="SBJ">
                                                        <observation classCode="OBS" moodCode="EVN">
                                                            <code code="4" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="ageGroup"/>
                                                            <value xsi:type="CE" code="{D23}" codeSystem="2.16.840.1.113883.3.989.2.1.1.9" codeSystemVersion="1.0"/>
                                                            <!-- D.2.3: Patient Age Group (as per reporter) -->
                                                        </observation>
                                                    </subjectOf2>
                                                </xsl:if>
                                                <xsl:if test="D3">
                                                    <subjectOf2 typeCode="SBJ">
                                                        <observation classCode="OBS" moodCode="EVN">
                                                            <code code="7" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="bodyWeight"/>
                                                            <value xsi:type="PQ" value="{D3}" unit="kg"/>
                                                            <!-- D.3: Body Weight (kg) -->
                                                        </observation>
                                                    </subjectOf2>
                                                </xsl:if>
                                                <xsl:if test="D4">
                                                    <subjectOf2 typeCode="SBJ">
                                                        <observation classCode="OBS" moodCode="EVN">
                                                            <code code="17" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="height"/>
                                                            <value xsi:type="PQ" value="{D4}" unit="cm"/>
                                                            <!-- D.4: Height (cm) -->
                                                        </observation>
                                                    </subjectOf2>
                                                </xsl:if>
                                                <xsl:if test="D6">
                                                    <subjectOf2 typeCode="SBJ">
                                                        <observation classCode="OBS" moodCode="EVN">
                                                            <code code="22" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="lastMenstrualPeriodDate"/>
                                                            <xsl:choose>
                                                                <xsl:when test="D6[@nf]">
                                                                    <value xsi:type="TS" nullFlavor="{D6/@nf}"/>
                                                                </xsl:when>
                                                                <xsl:otherwise>
                                                                    <value xsi:type="TS" value="{D6}"/>
                                                                </xsl:otherwise>
                                                            </xsl:choose>
                                                            <!-- D.6: Last Menstrual Period Date -->
                                                        </observation>
                                                    </subjectOf2>
                                                </xsl:if>
                                                <xsl:if test="D71r or D72 or D73">
                                                    <subjectOf2 typeCode="SBJ">
                                                        <organizer classCode="CATEGORY" moodCode="EVN">
                                                            <code code="1" codeSystem="2.16.840.1.113883.3.989.2.1.1.20" codeSystemVersion="1.0" displayName="relevantMedicalHistoryAndConcurrentConditions"/>
                                                            <xsl:for-each select="D71r">
                                                                <component typeCode="COMP">
                                                                    <observation classCode="OBS" moodCode="EVN">
                                                                        <xsl:if test="D71r1a or D71r1b">
                                                                            <code code="{D71r1b}" codeSystem="2.16.840.1.113883.6.163" codeSystemVersion="{D71r1a}"/>
                                                                            <!-- D.7.1.r.1a: MedDRA Version for Medical History #1 -->
                                                                            <!-- D.7.1.r.1b: Medical History (disease / surgical procedure / etc.) (MedDRA code) #1 -->
                                                                        </xsl:if>
                                                                        <xsl:if test="D71r2 or D71r4">
                                                                            <effectiveTime xsi:type="IVL_TS">
                                                                                <xsl:if test="D71r2">
                                                                                    <xsl:choose>
                                                                                        <xsl:when test="D71r2[@nf]">
                                                                                            <low nullFlavor="{D71r2/@nf}"/>
                                                                                        </xsl:when>
                                                                                        <xsl:otherwise>
                                                                                            <low value="{D71r2}"/>
                                                                                        </xsl:otherwise>
                                                                                    </xsl:choose>
                                                                                    <!-- D.7.1.r.2: Start Date #1 -->
                                                                                </xsl:if>
                                                                                <xsl:if test="D71r4">
                                                                                    <xsl:choose>
                                                                                        <xsl:when test="D71r4[@nf]">
                                                                                            <high nullFlavor="{D71r4/@nf}"/>
                                                                                        </xsl:when>
                                                                                        <xsl:otherwise>
                                                                                            <high value="{D71r4}"/>
                                                                                        </xsl:otherwise>
                                                                                    </xsl:choose>
                                                                                    <!-- D.7.1.r.4: End Date #1 -->
                                                                                </xsl:if>
                                                                            </effectiveTime>
                                                                        </xsl:if>
                                                                        <xsl:if test="D71r5">
                                                                            <outboundRelationship2 typeCode="COMP">
                                                                                <observation classCode="OBS" moodCode="EVN">
                                                                                    <code code="10" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="comment"/>
                                                                                    <value xsi:type="ED">
                                                                                        <xsl:value-of select="D71r5"/>
                                                                                    </value>
                                                                                    <!-- D.7.1.r.5: Comments #1 -->
                                                                                </observation>
                                                                            </outboundRelationship2>
                                                                        </xsl:if>
                                                                        <xsl:if test="D71r6">
                                                                            <outboundRelationship2 typeCode="EXPL">
                                                                                <observation classCode="OBS" moodCode="EVN">
                                                                                    <code code="38" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="familyHistory"/>
                                                                                    <value xsi:type="BL" value="{D71r6}"/>
                                                                                    <!-- D.7.1.r.6: Family History #1 -->
                                                                                </observation>
                                                                            </outboundRelationship2>
                                                                        </xsl:if>
                                                                        <xsl:if test="D71r3">
                                                                            <inboundRelationship typeCode="REFR">
                                                                                <observation classCode="OBS" moodCode="EVN">
                                                                                    <code code="13" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="continuing"/>
                                                                                    <xsl:choose>
                                                                                        <xsl:when test="D71r3[@nf]">
                                                                                            <value xsi:type="BL" nullFlavor="{D71r3/@nf}"/>
                                                                                        </xsl:when>
                                                                                        <xsl:otherwise>
                                                                                            <value xsi:type="BL" value="{D71r3}"/>
                                                                                        </xsl:otherwise>
                                                                                    </xsl:choose>
                                                                                    <!-- D.7.1.r.3: Continuing #1 -->
                                                                                </observation>
                                                                            </inboundRelationship>
                                                                        </xsl:if>
                                                                    </observation>
                                                                </component>
                                                            </xsl:for-each>
                                                            <xsl:if test="D72">
                                                                <component typeCode="COMP">
                                                                    <observation classCode="OBS" moodCode="EVN">
                                                                        <code code="18" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="historyAndConcurrentConditionText"/>
                                                                        <xsl:choose>
                                                                            <xsl:when test="D72[@nf]">
                                                                                <value xsi:type="ED" nullFlavor="{D72/@nf}"/>
                                                                            </xsl:when>
                                                                            <xsl:otherwise>
                                                                                <value xsi:type="ED">
                                                                                    <xsl:value-of select="D72"/>
                                                                                </value>
                                                                            </xsl:otherwise>
                                                                        </xsl:choose>
                                                                        <!-- D.7.2: Text for Relevant Medical History and Concurrent Conditions (not including reaction / event) -->
                                                                    </observation>
                                                                </component>
                                                            </xsl:if>
                                                            <xsl:if test="D73">
                                                                <component typeCode="COMP">
                                                                    <observation classCode="OBS" moodCode="EVN">
                                                                        <code code="11" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="concomitantTherapy"/>
                                                                        <value xsi:type="BL" value="{D73}"/>
                                                                        <!-- D.7.3:  Concomitant Therapies -->
                                                                    </observation>
                                                                </component>
                                                            </xsl:if>
                                                        </organizer>
                                                    </subjectOf2>
                                                </xsl:if>
                                                <xsl:if test="D8r">
                                                    <subjectOf2 typeCode="SBJ">
                                                        <organizer classCode="CATEGORY" moodCode="EVN">
                                                            <code code="2" codeSystem="2.16.840.1.113883.3.989.2.1.1.20" codeSystemVersion="1.0" displayName="drugHistory"/>
                                                            <xsl:for-each select="D8r">
                                                                <component typeCode="COMP">
                                                                    <substanceAdministration classCode="SBADM" moodCode="EVN">
                                                                        <xsl:if test="D8r4 or D8r5">
                                                                            <effectiveTime xsi:type="IVL_TS">
                                                                                <xsl:if test="D8r4">
                                                                                    <xsl:choose>
                                                                                        <xsl:when test="D8r4[@nf]">
                                                                                            <low nullFlavor="{D8r4/@nf}"/>
                                                                                        </xsl:when>
                                                                                        <xsl:otherwise>
                                                                                            <low value="{D8r4}"/>
                                                                                        </xsl:otherwise>
                                                                                    </xsl:choose>
                                                                                    <!-- D.8.r.4: Start Date #1 -->
                                                                                </xsl:if>
                                                                                <xsl:if test="D8r5">
                                                                                    <xsl:choose>
                                                                                        <xsl:when test="D8r5[@nf]">
                                                                                            <high nullFlavor="{D8r5/@nf}"/>
                                                                                        </xsl:when>
                                                                                        <xsl:otherwise>
                                                                                            <high value="{D8r5}"/>
                                                                                        </xsl:otherwise>
                                                                                    </xsl:choose>
                                                                                    <!-- D.8.r.5: End Date #1 -->
                                                                                </xsl:if>
                                                                            </effectiveTime>
                                                                        </xsl:if>
                                                                        <xsl:if test="D8r1 or D8r2a or D8r2b">
                                                                            <consumable typeCode="CSM">
                                                                                <instanceOfKind classCode="INST">
                                                                                    <kindOfProduct classCode="MMAT" determinerCode="KIND">
                                                                                        <xsl:if test="D8r2a or D8r2b">
                                                                                            <xsl:element name="code">
                                                                                                <xsl:attribute name="codeSystem">TBD-MPID</xsl:attribute>
                                                                                                <xsl:if test="D8r2a">
                                                                                                    <xsl:attribute name="codeSystemVersion">
                                                                                                        <xsl:value-of select="D8r2a"/>
                                                                                                    </xsl:attribute>
                                                                                                </xsl:if>
                                                                                                <xsl:if test="D8r2b">
                                                                                                    <xsl:attribute name="code">
                                                                                                        <xsl:value-of select="D8r2b"/>
                                                                                                    </xsl:attribute>
                                                                                                </xsl:if>
                                                                                            </xsl:element>
                                                                                            <!-- D.8.r.2a: MPID Version Date / Number #1 -->
                                                                                            <!-- D.8.r.2b: Medicinal Product Identifier (MPID) #1 -->
                                                                                        </xsl:if>
                                                                                        <xsl:if test="D8r3a or D8r3b">
                                                                                            <xsl:element name="code">
                                                                                                <xsl:attribute name="codeSystem">TBD-PhPID</xsl:attribute>
                                                                                                <xsl:if test="D8r3a">
                                                                                                    <xsl:attribute name="codeSystemVersion">
                                                                                                        <xsl:value-of select="D8r3a"/>
                                                                                                    </xsl:attribute>
                                                                                                </xsl:if>
                                                                                                <xsl:if test="D8r3b">
                                                                                                    <xsl:attribute name="code">
                                                                                                        <xsl:value-of select="D8r3b"/>
                                                                                                    </xsl:attribute>
                                                                                                </xsl:if>
                                                                                            </xsl:element>
                                                                                            <!-- D.8.r.2a: MPID Version Date / Number #1 -->
                                                                                            <!-- D.8.r.2b: Medicinal Product Identifier (MPID) #1 -->
                                                                                        </xsl:if>
                                                                                        <xsl:if test="D8r1">
                                                                                            <xsl:choose>
                                                                                                <xsl:when test="D8r1[@nf]">
                                                                                                    <name nullFlavor="{D8r1/@nf}"/>
                                                                                                </xsl:when>
                                                                                                <xsl:otherwise>
                                                                                                    <name>
                                                                                                        <xsl:value-of select="D8r1"/>
                                                                                                    </name>
                                                                                                </xsl:otherwise>
                                                                                            </xsl:choose>
                                                                                            <!-- D.8.r.1: Name of Drug as Reported #1 -->
                                                                                        </xsl:if>
                                                                                    </kindOfProduct>
                                                                                </instanceOfKind>
                                                                            </consumable>
                                                                        </xsl:if>
                                                                        <xsl:if test="D8r6a or D8r6b">
                                                                            <outboundRelationship2 typeCode="RSON">
                                                                                <observation classCode="OBS" moodCode="EVN">
                                                                                    <code code="19" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="indication"/>
                                                                                    <value xsi:type="CE" code="{D8r6a}" codeSystem="2.16.840.1.113883.6.163" codeSystemVersion="{D8r6b}"/>
                                                                                    <!-- D.8.r.6a: MedDRA Version for Indication #1 -->
                                                                                    <!-- D.8.r.6b: Indication (MedDRA code) #1 -->
                                                                                </observation>
                                                                            </outboundRelationship2>
                                                                        </xsl:if>
                                                                        <xsl:if test="D8r7a or D8r7b">
                                                                            <outboundRelationship2 typeCode="CAUS">
                                                                                <observation classCode="OBS" moodCode="EVN">
                                                                                    <code code="29" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="reaction"/>
                                                                                    <value xsi:type="CE" code="{D8r7b}" codeSystem="2.16.840.1.113883.6.163" codeSystemVersion="{D8r7a}"/>
                                                                                    <!-- D.8.r.7a: MedDRA Version for Reaction #1 -->
                                                                                    <!-- D.8.r.7b: Reaction (MedDRA code) #1 -->
                                                                                </observation>
                                                                            </outboundRelationship2>
                                                                        </xsl:if>
                                                                    </substanceAdministration>
                                                                </component>
                                                            </xsl:for-each>
                                                        </organizer>
                                                    </subjectOf2>
                                                </xsl:if>
                                                <xsl:for-each select="D92r">
                                                    <subjectOf2 typeCode="SBJ">
                                                        <observation classCode="OBS" moodCode="EVN">
                                                            <code code="32" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="reportedCauseOfDeath"/>
                                                            <value xsi:type="CE" code="{D92r1b}" codeSystem="2.16.840.1.113883.6.163" codeSystemVersion="{D92r1a}">
                                                                <!-- D.9.2.r.1a: MedDRA Version for Reported Cause(s) of Death #1 -->
                                                                <!-- D.9.2.r.1b: Reported Cause(s) of Death (MedDRA code) #1 -->
                                                                <originalText>
                                                                    <xsl:value-of select="D92r2"/>
                                                                </originalText>
                                                                <!-- D.9.2.r.2: Reported Cause(s) of Death (free text) #1 -->
                                                            </value>
                                                        </observation>
                                                    </subjectOf2>
                                                </xsl:for-each>
                                                <xsl:if test="D93 or D94r">
                                                    <subjectOf2 typeCode="SBJ">
                                                        <observation classCode="OBS" moodCode="EVN">
                                                            <code code="5" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="autopsy"/>
                                                            <xsl:if test="D93">
                                                                <xsl:choose>
                                                                    <xsl:when test="D93[@nf]">
                                                                        <value xsi:type="BL" nullFlavor="{D93/@nf}"/>
                                                                    </xsl:when>
                                                                    <xsl:otherwise>
                                                                        <value xsi:type="BL" value="{D93}"/>
                                                                    </xsl:otherwise>
                                                                </xsl:choose>
                                                                <!-- D.9.3  Was Autopsy Done?  -->
                                                            </xsl:if>
                                                            <xsl:for-each select="D94r">
                                                                <outboundRelationship2 typeCode="DRIV">
                                                                    <observation classCode="OBS" moodCode="EVN">
                                                                        <code code="8" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="causeOfDeath"/>
                                                                        <value xsi:type="CE" code="{D94r1b}" codeSystem="2.16.840.1.113883.6.163" codeSystemVersion="{D94r1a}">
                                                                            <!-- D.9.4.r.1a: MedDRA Version for Autopsy-determined Cause(s) of Death #1 -->
                                                                            <!-- D.9.4.r.1b Autopsy-determined Cause(s) of Death (MedDRA code) #1 -->
                                                                            <originalText>
                                                                                <xsl:value-of select="D94r2"/>
                                                                            </originalText>
                                                                            <!-- D.9.4.r.2: Autopsy-determined Cause(s) of Death (free text) #1 -->
                                                                        </value>
                                                                    </observation>
                                                                </outboundRelationship2>
                                                            </xsl:for-each>
                                                        </observation>
                                                    </subjectOf2>
                                                </xsl:if>
                                                <xsl:for-each select="Ei">
                                                    <subjectOf2 typeCode="SBJ">
                                                        <observation classCode="OBS" moodCode="EVN">
                                                            <id root="154eb889-958b-45f2-a02f-42d4d6f4657f"/>
                                                            <code code="29" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="reaction"/>
                                                            <xsl:if test="(Ei4 or Ei5 or Ei6a or Ei6b) and not(Ei4 and Ei5 and Ei6a and Ei6b)">
                                                                <effectiveTime xsi:type="IVL_TS">
                                                                    <xsl:if test="Ei4">
                                                                        <xsl:choose>
                                                                            <xsl:when test="Ei4[@nf]">
                                                                                <low nullFlavor="{Ei4/@nf}"/>
                                                                            </xsl:when>
                                                                            <xsl:otherwise>
                                                                                <low value="{Ei4}"/>
                                                                            </xsl:otherwise>
                                                                        </xsl:choose>
                                                                        <!-- E.i.4: Date of Start of Reaction / Event #1 -->
                                                                    </xsl:if>
                                                                    <xsl:if test="Ei6a or Ei6b">
                                                                        <width value="{Ei6a}" unit="{Ei6b}"/>
                                                                        <!-- E.i.6a: Duration of Reaction / Event -->
                                                                        <!-- E.i.6b: Duration of Reaction / Event (Duration Unit) -->
                                                                    </xsl:if>
                                                                    <xsl:if test="Ei5">
                                                                        <xsl:choose>
                                                                            <xsl:when test="Ei5[@nf]">
                                                                                <high nullFlavor="{Ei5/@nf}"/>
                                                                            </xsl:when>
                                                                            <xsl:otherwise>
                                                                                <high value="{Ei5}"/>
                                                                            </xsl:otherwise>
                                                                        </xsl:choose>
                                                                        <!-- E.i.5: Date of End of Reaction / Event #1 -->
                                                                    </xsl:if>
                                                                </effectiveTime>
                                                            </xsl:if>
                                                            <xsl:if test="Ei4 and Ei5 and Ei6a and Ei6b">
                                                                <effectiveTime xsi:type="SXPR_TS">
                                                                    <comp xsi:type="IVL_TS">
                                                                        <xsl:choose>
                                                                            <xsl:when test="Ei4[@nf]">
                                                                                <low nullFlavor="{Ei4/@nf}"/>
                                                                            </xsl:when>
                                                                            <xsl:otherwise>
                                                                                <low value="{Ei4}"/>
                                                                            </xsl:otherwise>
                                                                        </xsl:choose>
                                                                        <!-- E.i.4 Date of Start of Reaction / Event -->
                                                                        <xsl:choose>
                                                                            <xsl:when test="Ei5[@nf]">
                                                                                <high nullFlavor="{Ei5/@nf}"/>
                                                                            </xsl:when>
                                                                            <xsl:otherwise>
                                                                                <high value="{Ei5}"/>
                                                                            </xsl:otherwise>
                                                                        </xsl:choose>
                                                                        <!-- E.i.5: Date of End of Reaction / Event -->
                                                                    </comp>
                                                                    <comp xsi:type="IVL_TS" operator="A">
                                                                        <width value="{Ei6a}" unit="{Ei6b}"/>
                                                                        <!-- E.i.6a: Duration of Reaction / Event -->
                                                                        <!-- E.i.6b: Duration of Reaction / Event (Duration Unit) -->
                                                                    </comp>
                                                                </effectiveTime>
                                                            </xsl:if>
                                                            <value xsi:type="CE" code="{Ei21b}" codeSystem="2.16.840.1.113883.6.163" codeSystemVersion="{Ei21a}">
                                                                <!-- E.i.2.1a: MedDRA Version for Reaction / Event #1 -->
                                                                <!-- E.i.2.1b: Reaction / Event (MedDRA code) #1 -->
                                                                <xsl:if test="Ei11a or Ei11b">
                                                                    <originalText language="{Ei11b}">
                                                                        <xsl:value-of select="Ei11a"/>
                                                                    </originalText>
                                                                    <!-- E.i.1.1a: Reaction / Event as Reported by the Primary Source in Native Language #1 -->
                                                                    <!-- E.i.1.1b: Reaction / Event as Reported by the Primary Source Language #1 -->
                                                                </xsl:if>
                                                            </value>
                                                            <xsl:if test="Ei9">
                                                                <location typeCode="LOC">
                                                                    <locatedEntity classCode="LOCE">
                                                                        <locatedPlace classCode="COUNTRY" determinerCode="INSTANCE">
                                                                            <code code="{Ei9}" codeSystem="1.0.3166.1.2.2"/>
                                                                            <!-- E.i.9: Identification of the Country Where the Reaction / Event Occurred #1 -->
                                                                        </locatedPlace>
                                                                    </locatedEntity>
                                                                </location>
                                                            </xsl:if>
                                                            <xsl:if test="Ei12">
                                                                <outboundRelationship2 typeCode="PERT">
                                                                    <observation classCode="OBS" moodCode="EVN">
                                                                        <code code="30" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="reactionForTranslation"/>
                                                                        <value xsi:type="ED">
                                                                            <xsl:value-of select="Ei12"/>
                                                                        </value>
                                                                        <!-- E.i.1.2: Reaction / Event as Reported by the Primary Source for Translation #1 -->
                                                                    </observation>
                                                                </outboundRelationship2>
                                                            </xsl:if>
                                                            <xsl:if test="Ei31">
                                                                <outboundRelationship2 typeCode="PERT">
                                                                    <observation classCode="OBS" moodCode="EVN">
                                                                        <code code="37" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="termHighlightedByReporter"/>
                                                                        <value xsi:type="CE" code="{Ei31}" codeSystem="2.16.840.1.113883.3.989.2.1.1.10" codeSystemVersion="1.0"/>
                                                                        <!-- E.i.3.1: Term Highlighted by the Reporter #1 -->
                                                                    </observation>
                                                                </outboundRelationship2>
                                                            </xsl:if>
                                                            <xsl:if test="Ei32a">
                                                                <outboundRelationship2 typeCode="PERT">
                                                                    <observation classCode="OBS" moodCode="EVN">
                                                                        <code code="34" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="resultsInDeath"/>
                                                                        <xsl:choose>
                                                                            <xsl:when test="Ei32a[@nf]">
                                                                                <value xsi:type="BL" nullFlavor="{Ei32a/@nf}"/>
                                                                            </xsl:when>
                                                                            <xsl:otherwise>
                                                                                <value xsi:type="BL" value="{Ei32a}"/>
                                                                            </xsl:otherwise>
                                                                        </xsl:choose>
                                                                        <!-- E.i.3.2a: Results in Death #1 -->
                                                                    </observation>
                                                                </outboundRelationship2>
                                                            </xsl:if>
                                                            <xsl:if test="Ei32b">
                                                                <outboundRelationship2 typeCode="PERT">
                                                                    <observation classCode="OBS" moodCode="EVN">
                                                                        <code code="21" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="isLifeThreatening"/>
                                                                        <xsl:choose>
                                                                            <xsl:when test="Ei32b[@nf]">
                                                                                <value xsi:type="BL" nullFlavor="{Ei32b/@nf}"/>
                                                                            </xsl:when>
                                                                            <xsl:otherwise>
                                                                                <value xsi:type="BL" value="{Ei32b}"/>
                                                                            </xsl:otherwise>
                                                                        </xsl:choose>
                                                                        <!-- E.i.3.2b:  Life Threatening #1 -->
                                                                    </observation>
                                                                </outboundRelationship2>
                                                            </xsl:if>
                                                            <xsl:if test="Ei32c">
                                                                <outboundRelationship2 typeCode="PERT">
                                                                    <observation classCode="OBS" moodCode="EVN">
                                                                        <code code="33" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="requiresInpatientHospitalization"/>
                                                                        <xsl:choose>
                                                                            <xsl:when test="Ei32c[@nf]">
                                                                                <value xsi:type="BL" nullFlavor="{Ei32c/@nf}"/>
                                                                            </xsl:when>
                                                                            <xsl:otherwise>
                                                                                <value xsi:type="BL" value="{Ei32c}"/>
                                                                            </xsl:otherwise>
                                                                        </xsl:choose>
                                                                        <!-- E.i.3.2c: Caused / Prolonged Hospitalisation #1 -->
                                                                    </observation>
                                                                </outboundRelationship2>
                                                            </xsl:if>
                                                            <xsl:if test="Ei32d">
                                                                <outboundRelationship2 typeCode="PERT">
                                                                    <observation classCode="OBS" moodCode="EVN">
                                                                        <code code="35" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="resultsInPersistentOrSignificantDisability"/>
                                                                        <xsl:choose>
                                                                            <xsl:when test="Ei32d[@nf]">
                                                                                <value xsi:type="BL" nullFlavor="{Ei32d/@nf}"/>
                                                                            </xsl:when>
                                                                            <xsl:otherwise>
                                                                                <value xsi:type="BL" value="{Ei32d}"/>
                                                                            </xsl:otherwise>
                                                                        </xsl:choose>
                                                                        <!-- E.i.3.2d: Disabling / Incapacitating #1-->
                                                                    </observation>
                                                                </outboundRelationship2>
                                                            </xsl:if>
                                                            <xsl:if test="Ei32e">
                                                                <outboundRelationship2 typeCode="PERT">
                                                                    <observation classCode="OBS" moodCode="EVN">
                                                                        <code code="12" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="congenitalAnomalyBirthDefect"/>
                                                                        <xsl:choose>
                                                                            <xsl:when test="Ei32e[@nf]">
                                                                                <value xsi:type="BL" nullFlavor="{Ei32e/@nf}"/>
                                                                            </xsl:when>
                                                                            <xsl:otherwise>
                                                                                <value xsi:type="BL" value="{Ei32e}"/>
                                                                            </xsl:otherwise>
                                                                        </xsl:choose>
                                                                        <!-- E.i.3.2e: Congenital Anomaly / Birth Defect #1 -->
                                                                    </observation>
                                                                </outboundRelationship2>
                                                            </xsl:if>
                                                            <xsl:if test="Ei32f">
                                                                <outboundRelationship2 typeCode="PERT">
                                                                    <observation classCode="OBS" moodCode="EVN">
                                                                        <code code="26" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="otherMedicallyImportantCondition"/>
                                                                        <xsl:choose>
                                                                            <xsl:when test="Ei32f[@nf]">
                                                                                <value xsi:type="BL" nullFlavor="{Ei32f/@nf}"/>
                                                                            </xsl:when>
                                                                            <xsl:otherwise>
                                                                                <value xsi:type="BL" value="{Ei32f}"/>
                                                                            </xsl:otherwise>
                                                                        </xsl:choose>
                                                                        <!-- E.i.3.2f: Other Medically Important Condition #1 -->
                                                                    </observation>
                                                                </outboundRelationship2>
                                                            </xsl:if>
                                                            <xsl:if test="Ei7">
                                                                <outboundRelationship2 typeCode="PERT">
                                                                    <observation classCode="OBS" moodCode="EVN">
                                                                        <code code="27" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="outcome"/>
                                                                        <value xsi:type="CE" code="{Ei7}" codeSystem="2.16.840.1.113883.3.989.2.1.1.11" codeSystemVersion="1.0"/>
                                                                        <!-- E.i.7: Outcome of Reaction / Event at the Time of Last Observation #1 -->
                                                                    </observation>
                                                                </outboundRelationship2>
                                                            </xsl:if>
                                                            <xsl:if test="Ei8">
                                                                <outboundRelationship2 typeCode="PERT">
                                                                    <observation classCode="OBS" moodCode="EVN">
                                                                        <code code="24" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="medicalConfirmationByHealthProfessional"/>
                                                                        <value xsi:type="BL" value="{Ei8}"/>
                                                                        <!-- E.i.8: Medical Confirmation by Healthcare Professional #1 -->
                                                                    </observation>
                                                                </outboundRelationship2>
                                                            </xsl:if>
                                                        </observation>
                                                    </subjectOf2>
                                                </xsl:for-each>
                                                <xsl:if test="Fr">
                                                    <subjectOf2 typeCode="SBJ">
                                                        <organizer classCode="CATEGORY" moodCode="EVN">
                                                            <code code="3" codeSystem="2.16.840.1.113883.3.989.2.1.1.20" codeSystemVersion="1.0" displayName="testsAndProceduresRelevantToTheInvestigation"/>
                                                            <xsl:for-each select="Fr">
                                                                <component typeCode="COMP">
                                                                    <observation classCode="OBS" moodCode="EVN">
                                                                        <xsl:if test="Fr21 or Fr22a or Fr22b">
                                                                            <code code="{Fr22b}" codeSystem="2.16.840.1.113883.6.163" codeSystemVersion="{Fr22a}">
                                                                                <!-- F.r.2.2a: MedDRA Version for Test Name #1 -->
                                                                                <!-- F.r.2.2b: Test Name (MedDRA code) #1 -->
                                                                                <originalText>
                                                                                    <xsl:value-of select="Fr21"/>
                                                                                </originalText>
                                                                                <!-- F.r.2.1: Test Name (free text) #1 -->
                                                                            </code>
                                                                        </xsl:if>
                                                                        <xsl:if test="Fr1">
                                                                            <xsl:choose>
                                                                                <xsl:when test="Fr1[@nf]">
                                                                                    <effectiveTime nullFlavor="{Fr1/@nf}"/>
                                                                                </xsl:when>
                                                                                <xsl:otherwise>
                                                                                    <effectiveTime value="{Fr1}"/>
                                                                                </xsl:otherwise>
                                                                            </xsl:choose>
                                                                            <!-- F.r.1: Test Date #1 -->
                                                                        </xsl:if>
                                                                        <xsl:if test="Fr32 or Fr33">
                                                                            <value xsi:type="IVL_PQ">
                                                                                <xsl:choose>
                                                                                    <xsl:when test="Fr32[@qualifier='LT']">
                                                                                        <low nullFlavor="NINF"/>
                                                                                        <high value="{Fr32}" unit="{Fr33}" inclusive="false"/>
                                                                                    </xsl:when>
                                                                                    <xsl:when test="Fr32[@qualifier='GT']">
                                                                                        <low value="{Fr32}" unit="{Fr33}" inclusive="false"/>
                                                                                        <high nullFlavor="PINF"/>
                                                                                    </xsl:when>
                                                                                    <xsl:when test="Fr32[@qualifier='LE']">
                                                                                        <low nullFlavor="NINF"/>
                                                                                        <high value="{Fr32}" unit="{Fr33}" inclusive="true"/>
                                                                                    </xsl:when>
                                                                                    <xsl:when test="Fr32[@qualifier='GE']">
                                                                                        <low value="{Fr32}" unit="{Fr33}" inclusive="true"/>
                                                                                        <high nullFlavor="PINF"/>
                                                                                    </xsl:when>
                                                                                    <xsl:otherwise>
                                                                                        <center value="{Fr32}" unit="{Fr33}"/>
                                                                                    </xsl:otherwise>
                                                                                </xsl:choose>
                                                                                <!-- F.r.3.2: Test Result (value / qualifier) #1 -->
                                                                                <!-- F.r.3.3: Test Result (unit) #1 -->
                                                                            </value>
                                                                        </xsl:if>
                                                                        <xsl:if test="Fr31">
                                                                            <interpretationCode code="{Fr31}" codeSystem="2.16.840.1.113883.3.989.2.1.1.12" codeSystemVersion="1.0"/>
                                                                            <!-- F.r.3.1: Test Result (code) #1 -->
                                                                        </xsl:if>
                                                                        <xsl:if test="Fr34">
                                                                            <value xsi:type="ED">
                                                                                <xsl:value-of select="Fr34"/>
                                                                            </value>
                                                                            <!-- F.r.3.4: Result Unstructured Data (free text) #6 -->
                                                                        </xsl:if>
                                                                        <xsl:if test="Fr4">
                                                                            <referenceRange typeCode="REFV">
                                                                                <observationRange classCode="OBS" moodCode="EVN.CRT">
                                                                                    <value xsi:type="PQ" value="{Fr4}" unit="{Fr33}"/>
                                                                                    <!-- F.r.4: Normal Low Value #1 -->
                                                                                    <interpretationCode code="L" codeSystem="2.16.840.1.113883.5.83"/>
                                                                                </observationRange>
                                                                            </referenceRange>
                                                                        </xsl:if>
                                                                        <xsl:if test="Fr5">
                                                                            <referenceRange typeCode="REFV">
                                                                                <observationRange classCode="OBS" moodCode="EVN.CRT">
                                                                                    <value xsi:type="PQ" value="{Fr5}" unit="{Fr33}"/>
                                                                                    <!-- F.r.5:  Normal High Value #1-->
                                                                                    <interpretationCode code="H" codeSystem="2.16.840.1.113883.5.83"/>
                                                                                </observationRange>
                                                                            </referenceRange>
                                                                        </xsl:if>
                                                                        <xsl:if test="Fr6">
                                                                            <outboundRelationship2 typeCode="PERT">
                                                                                <observation classCode="OBS" moodCode="EVN">
                                                                                    <code code="10" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="comment"/>
                                                                                    <value xsi:type="ED">
                                                                                        <xsl:value-of select="Fr6"/>
                                                                                    </value>
                                                                                    <!-- F.r.6: Comments (free text) #1 -->
                                                                                </observation>
                                                                            </outboundRelationship2>
                                                                        </xsl:if>
                                                                        <xsl:if test="Fr7">
                                                                            <outboundRelationship2 typeCode="REFR">
                                                                                <observation classCode="OBS" moodCode="EVN">
                                                                                    <code code="25" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="moreInformationAvailable"/>
                                                                                    <value xsi:type="BL" value="{Fr7}"/>
                                                                                    <!--F.r.7: More Information Available #1 -->
                                                                                </observation>
                                                                            </outboundRelationship2>
                                                                        </xsl:if>
                                                                    </observation>
                                                                </component>
                                                            </xsl:for-each>
                                                        </organizer>
                                                    </subjectOf2>
                                                </xsl:if>
                                                <subjectOf2 typeCode="SBJ">
                                                    <organizer classCode="CATEGORY" moodCode="EVN">
                                                        <code code="4" codeSystem="2.16.840.1.113883.3.989.2.1.1.20" codeSystemVersion="1.0" displayName="drugInformation"/>
                                                        <xsl:for-each select="Gk">
                                                            <!-- G.k Drug(s) Information (repeat as necessary) #1 -->
                                                            <component typeCode="COMP">
                                                                <substanceAdministration classCode="SBADM" moodCode="EVN">
                                                                    <id root="3c91b4d5-e039-4a7a-9c30-67671b0ef9e4"/> <!-- ??????? -->
                                                                    <consumable typeCode="CSM">
                                                                        <instanceOfKind classCode="INST">
                                                                            <kindOfProduct classCode="MMAT" determinerCode="KIND">
                                                                                <xsl:if test="Gk211a or Gk211b">
                                                                                    <code code="{Gk211b}" codeSystem="TBD-MPID" codeSystemVersion="{Gk211a}"/>
                                                                                    <!-- G.k.2.1.1a: MPID Version Date / Number #1 -->
                                                                                    <!-- G.k.2.1.1b: Medicinal Product Identifier (MPID) #1 -->
                                                                                </xsl:if>
                                                                                <xsl:if test="Gk22">
                                                                                    <name>
                                                                                        <xsl:value-of select="Gk22"/>
                                                                                    </name>
                                                                                    <!-- G.k.2.2: Medicinal Product Name as Reported by the Primary Source #1 -->
                                                                                </xsl:if>
                                                                                <xsl:if test="Gk31 or Gk32 or Gk33">
                                                                                    <asManufacturedProduct classCode="MANU">
                                                                                        <subjectOf typeCode="SBJ">
                                                                                            <approval classCode="CNTRCT" moodCode="EVN">
                                                                                                <xsl:if test="Gk31">
                                                                                                    <id extension="{Gk31}" root="2.16.840.1.113883.3.989.2.1.3.4"/>
                                                                                                    <!-- G.k.3.1: Authorisation / Application Number #1 -->
                                                                                                </xsl:if>
                                                                                                <xsl:if test="Gk33">
                                                                                                    <holder typeCode="HLD">
                                                                                                        <role classCode="HLD">
                                                                                                            <playingOrganization classCode="ORG" determinerCode="INSTANCE">
                                                                                                                <name>
                                                                                                                    <xsl:value-of select="Gk33"/>
                                                                                                                </name>
                                                                                                                <!-- G.k.3.3: Name of Holder / Applicant #1 -->
                                                                                                            </playingOrganization>
                                                                                                        </role>
                                                                                                    </holder>
                                                                                                </xsl:if>
                                                                                                <xsl:if test="Gk32">
                                                                                                    <author typeCode="AUT">
                                                                                                        <territorialAuthority classCode="TERR">
                                                                                                            <territory classCode="NAT" determinerCode="INSTANCE">
                                                                                                                <code code="{Gk32}" codeSystem="1.0.3166.1.2.2"/>
                                                                                                                <!-- G.k.3.2: Country of Authorisation / Application #1 -->
                                                                                                            </territory>
                                                                                                        </territorialAuthority>
                                                                                                    </author>
                                                                                                </xsl:if>
                                                                                            </approval>
                                                                                        </subjectOf>
                                                                                    </asManufacturedProduct>
                                                                                </xsl:if>
                                                                                <xsl:for-each select="Gk23r">
                                                                                    <ingredient classCode="ACTI">
                                                                                        <xsl:if test="Gk23r3a or Gk23r3b">
                                                                                            <quantity>
                                                                                                <numerator value="{Gk23r3a}" unit="{Gk23r3b}"/>
                                                                                                <!-- G.k.2.3.r.3a: Strength (number) #1-1 -->
                                                                                                <!-- G.k.2.3.r.3b: Strength (unit) #1-1 -->
                                                                                                <denominator value="1"/>
                                                                                            </quantity>
                                                                                        </xsl:if>
                                                                                        <xsl:if test="Gk23r1 or Gk23r2a or Gk23r2b">
                                                                                            <ingredientSubstance classCode="MMAT" determinerCode="KIND">
                                                                                                <xsl:if test="Gk23r2a or Gk23r2b">
                                                                                                    <xsl:element name="code">
                                                                                                        <xsl:attribute name="code">
                                                                                                            <xsl:value-of select="Gk23r2b"/>
                                                                                                        </xsl:attribute>
                                                                                                        <xsl:attribute name="codeSystem">TBD-MPID</xsl:attribute>
                                                                                                        <xsl:attribute name="codeSystemVersion">
                                                                                                            <xsl:value-of select="Gk23r2a"/>
                                                                                                        </xsl:attribute>
                                                                                                        <!-- G.k.2.3.r.2a: Substance / Specified Substance TermID Version Date / Number #1-1 -->
                                                                                                        <!-- G.k.2.3.r.2b: Substance / Specified Substance TermID #1-1 -->
                                                                                                    </xsl:element>
                                                                                                </xsl:if>
                                                                                                <xsl:if test="Gk23r1">
                                                                                                    <name>
                                                                                                        <xsl:value-of select="Gk23r1"/>
                                                                                                    </name>
                                                                                                    <!-- G.k.2.3.r.1: Substance / Specified Substance Name #1-1 -->
                                                                                                </xsl:if>
                                                                                            </ingredientSubstance>
                                                                                        </xsl:if>
                                                                                    </ingredient>
                                                                                </xsl:for-each>
                                                                            </kindOfProduct>
                                                                            <xsl:if test="Gk24">
                                                                                <subjectOf typeCode="SBJ">
                                                                                    <productEvent classCode="ACT" moodCode="EVN">
                                                                                        <code code="1" codeSystem="2.16.840.1.113883.3.989.2.1.1.18" codeSystemVersion="1.0" displayName="retailSupply"/>
                                                                                        <performer typeCode="PRF">
                                                                                            <assignedEntity classCode="ASSIGNED">
                                                                                                <representedOrganization classCode="ORG" determinerCode="INSTANCE">
                                                                                                    <addr>
                                                                                                        <country>
                                                                                                            <xsl:value-of select="Gk24"/>
                                                                                                        </country>
                                                                                                        <!-- G.k.2.4: Identification of the Country Where the Drug Was Obtained #1 -->
                                                                                                    </addr>
                                                                                                </representedOrganization>
                                                                                            </assignedEntity>
                                                                                        </performer>
                                                                                    </productEvent>
                                                                                </subjectOf>
                                                                            </xsl:if>
                                                                        </instanceOfKind>
                                                                    </consumable>
                                                                    <xsl:for-each select="Gk9i">
                                                                        <xsl:if test="Gk9i31a or Gk9i31b">
                                                                            <outboundRelationship1 typeCode="SAS">
                                                                                <pauseQuantity value="{Gk9i31a}" unit="{Gk9i31b}"/>
                                                                                <!-- G.k.9.i.3.1a: Time Interval between Beginning of Drug Administration and Start of Reaction / Event (number) Drug #1, Reaction #1 -->
                                                                                <!-- G.k.9.i.3.1b: Time Interval between Beginning of Drug Administration and Start of Reaction / Event (unit)  Drug #1, Reaction #1 -->
                                                                                <actReference classCode="OBS" moodCode="EVN">
                                                                                    <id root="154eb889-958b-45f2-a02f-42d4d6f4657f"/>
                                                                                </actReference>
                                                                            </outboundRelationship1>
                                                                        </xsl:if>
                                                                        <xsl:if test="Gk9i32a or Gk9i32b">
                                                                            <outboundRelationship1 typeCode="SAE">
                                                                                <pauseQuantity value="{Gk9i32a}" unit="{Gk9i32b}"/>
                                                                                <!-- G.k.9.i.3.2a: Time Interval between Last Dose of Drug and Start of Reaction / Event (number)  Drug #1, Reaction #1 -->
                                                                                <!-- G.k.9.i.3.2b: Time Interval between Last Dose of Drug and Start of Reaction / Event (unit)  Drug #1, Reaction #1 -->
                                                                                <actReference classCode="OBS" moodCode="EVN">
                                                                                    <id root="154eb889-958b-45f2-a02f-42d4d6f4657f"/>
                                                                                </actReference>
                                                                            </outboundRelationship1>
                                                                        </xsl:if>
                                                                    </xsl:for-each>
                                                                    <xsl:if test="Gk25">
                                                                        <outboundRelationship2 typeCode="PERT">
                                                                            <observation classCode="OBS" moodCode="EVN">
                                                                                <code code="6" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="blinded"/>
                                                                                <value xsi:type="BL" value="{Gk25}"/>
                                                                                <!-- G.k.2.5: Investigational Product Blinded #1 -->
                                                                            </observation>
                                                                        </outboundRelationship2>
                                                                    </xsl:if>
                                                                    <xsl:for-each select="Gk4r">
                                                                        <outboundRelationship2 typeCode="COMP">
                                                                            <!-- G.k.4.r: Dosage and Relevant Information (repeat as necessary) #1-1 -->
                                                                            <substanceAdministration classCode="SBADM" moodCode="EVN">
                                                                                <xsl:if test="Gk4r8">
                                                                                    <text>
                                                                                        <xsl:value-of select="Gk4r8"/>
                                                                                    </text>
                                                                                    <!-- G.k.4.r.8: Dosage Text #1-1 -->
                                                                                </xsl:if>
                                                                                <xsl:if test="Gk4r2 or Gk4r3 or Gk4r4 or Gk4r5 or Gk4r6a or Gk4r6b">
                                                                                    <effectiveTime xsi:type="SXPR_TS">
                                                                                        <xsl:if test="Gk4r2 or Gk4r3">
                                                                                            <comp xsi:type="PIVL_TS">
                                                                                                <period value="{Gk4r2}" unit="{Gk4r3}"/>
                                                                                                <!-- G.k.4.r.2: Number of Units in the Interval #1-1 -->
                                                                                                <!-- G.k.4.r.3: Definition of the Time Interval Unit #1-1 -->
                                                                                            </comp>
                                                                                        </xsl:if>
                                                                                        <xsl:if test="Gk4r4 or Gk4r5">
                                                                                            <comp xsi:type="IVL_TS" operator="A">
                                                                                                <xsl:if test="Gk4r4">
                                                                                                    <xsl:choose>
                                                                                                        <xsl:when test="Gk4r4[@nf]">
                                                                                                            <low nullFlavor="{Gk4r4/@nf}"/>
                                                                                                        </xsl:when>
                                                                                                        <xsl:otherwise>
                                                                                                            <low value="{Gk4r4}"/>
                                                                                                        </xsl:otherwise>
                                                                                                    </xsl:choose>
                                                                                                    <!-- G.k.4.r.4: Date and Time of Start of Drug #1-1 -->
                                                                                                </xsl:if>
                                                                                                <xsl:if test="Gk4r5">
                                                                                                    <xsl:choose>
                                                                                                        <xsl:when test="Gk4r5[@nf]">
                                                                                                            <high nullFlavor="{Gk4r5/@nf}"/>
                                                                                                        </xsl:when>
                                                                                                        <xsl:otherwise>
                                                                                                            <high value="{Gk4r5}"/>
                                                                                                        </xsl:otherwise>
                                                                                                    </xsl:choose>
                                                                                                    <!-- G.k.4.r.5: Date and Time of Last Administration #1-1 -->
                                                                                                </xsl:if>
                                                                                            </comp>
                                                                                        </xsl:if>
                                                                                        <xsl:if test="Gk4r6a or Gk4r6b">
                                                                                            <comp xsi:type="IVL_TS" operator="A">
                                                                                                <width value="{Gk4r6a}" unit="{Gk4r6b}"/>
                                                                                                <!-- G.k.4.r.6a: Duration of Drug Administration (number) #1-1 -->
                                                                                                <!-- G.k.4.r.6b: Duration of Drug Administration (unit) #1-1 -->
                                                                                            </comp>
                                                                                        </xsl:if>
                                                                                    </effectiveTime>
                                                                                </xsl:if>
                                                                                <xsl:if test="Gk4r101 or Gk4r102a or Gk4r102b">
                                                                                    <xsl:element name="routeCode">
                                                                                        <xsl:if test="Gk4r102b">
                                                                                            <xsl:attribute name="code">
                                                                                                <xsl:value-of select="Gk4r102b"/>
                                                                                            </xsl:attribute>
                                                                                        </xsl:if>
                                                                                        <xsl:attribute name="codeSystem">2.16.840.1.113883.3.989.2.1.1.14</xsl:attribute>
                                                                                        <xsl:if test="Gk4r102a">
                                                                                            <xsl:attribute name="codeSystemVersion">
                                                                                                <xsl:value-of select="Gk4r102a"/>
                                                                                            </xsl:attribute>
                                                                                        </xsl:if>
                                                                                        <!-- G.k.4.r.10.2a: Route of Administration TermID Version Date / Number #1-1 -->
                                                                                        <!-- G.k.4.r.10.2b: Route of Administration TermID #1-1 -->
                                                                                        <xsl:if test="Gk4r101">
                                                                                            <xsl:choose>
                                                                                                <xsl:when test="Gk4r101[@nf]">
                                                                                                    <originalText nullFlavor="{Gk4r101/@nf}"/>
                                                                                                </xsl:when>
                                                                                                <xsl:otherwise>
                                                                                                    <originalText>
                                                                                                        <xsl:value-of select="Gk4r101"/>
                                                                                                    </originalText>
                                                                                                </xsl:otherwise>
                                                                                            </xsl:choose>
                                                                                            <!-- G.k.4.r.10.1: Route of Administration (free text) #1-1 -->
                                                                                        </xsl:if>
                                                                                    </xsl:element>
                                                                                </xsl:if>
                                                                                <xsl:if test="Gk4r1a or Gk4r1b">
                                                                                    <doseQuantity value="{Gk4r1a}" unit="{Gk4r1b}"/>
                                                                                    <!-- G.k.4.r.1a Dose (number) #1-1 -->
                                                                                    <!-- G.k.4.r.1b: Dose (unit) #1-1 -->
                                                                                </xsl:if>
                                                                                <xsl:if test="Gk4r7 or Gk4r91 or Gk4r92a or Gk4r92b">
                                                                                    <consumable typeCode="CSM">
                                                                                        <instanceOfKind classCode="INST">
                                                                                            <xsl:if test="Gk4r7">
                                                                                                <productInstanceInstance classCode="MMAT" determinerCode="INSTANCE">
                                                                                                    <lotNumberText>
                                                                                                        <xsl:value-of select="Gk4r7"/>
                                                                                                    </lotNumberText>
                                                                                                    <!-- G.k.4.r.7: Batch / Lot Number #1-1 -->
                                                                                                </productInstanceInstance>
                                                                                            </xsl:if>
                                                                                            <xsl:if test="Gk4r91 or Gk4r92a or Gk4r92b">
                                                                                                <kindOfProduct classCode="MMAT" determinerCode="KIND">
                                                                                                    <xsl:element name="formCode">
                                                                                                        <xsl:if test="Gk4r92b">
                                                                                                            <xsl:attribute name="code">
                                                                                                                <xsl:value-of select="Gk4r92b"/>
                                                                                                            </xsl:attribute>
                                                                                                        </xsl:if>
                                                                                                        <xsl:attribute name="codeSystem">TBD-DoseForm</xsl:attribute>
                                                                                                        <xsl:if test="Gk4r92b">
                                                                                                            <xsl:attribute name="codeSystemVersion">
                                                                                                                <xsl:value-of select="Gk4r92a"/>
                                                                                                            </xsl:attribute>
                                                                                                        </xsl:if>
                                                                                                        <!-- G.k.4.r.9.2a: Pharmaceutical Dose Form TermID Version Date / Number #1-1 -->
                                                                                                        <!-- G.k.4.r.9.2b: Pharmaceutical Dose Form TermID #1-1 -->
                                                                                                        <xsl:if test="Gk4r91">
                                                                                                            <xsl:choose>
                                                                                                                <xsl:when test="Gk4r91[@nf]">
                                                                                                                    <originalTest nullFlavor="{Gk4r91/@nf}"/>
                                                                                                                </xsl:when>
                                                                                                                <xsl:otherwise>
                                                                                                                    <originalText>
                                                                                                                        <xsl:value-of select="Gk4r91"/>
                                                                                                                    </originalText>
                                                                                                                </xsl:otherwise>
                                                                                                            </xsl:choose>
                                                                                                            <!-- G.k.4.r.9.1: Pharmaceutical Dose Form (free text) #1-1 -->
                                                                                                        </xsl:if>
                                                                                                    </xsl:element>
                                                                                                </kindOfProduct>
                                                                                            </xsl:if>
                                                                                        </instanceOfKind>
                                                                                    </consumable>
                                                                                </xsl:if>
                                                                                <xsl:if test="Gk4r111 or Gk4r112a or Gk4r112b">
                                                                                    <inboundRelationship typeCode="REFR">
                                                                                        <observation classCode="OBS" moodCode="EVN">
                                                                                            <code code="28" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="parentRouteOfAdministration"/>
                                                                                            <xsl:element name="value">
                                                                                                <xsl:attribute name="xsi:type">CE</xsl:attribute>
                                                                                                
                                                                                                <xsl:if test="Gk4r112b">
                                                                                                    <xsl:attribute name="code">
                                                                                                        <xsl:value-of select="Gk4r112b"/>
                                                                                                    </xsl:attribute>
                                                                                                </xsl:if>
                                                                                                <xsl:attribute name="codeSystem">2.16.840.1.113883.3.989.2.1.1.14</xsl:attribute>
                                                                                                <xsl:if test="Gk4r112a">
                                                                                                    <xsl:attribute name="codeSystemVersion">
                                                                                                        <xsl:value-of select="Gk4r112a"/>
                                                                                                    </xsl:attribute>
                                                                                                </xsl:if>
                                                                                                <!-- G.k.4.r.11.2a: Parent Route of Administration TermID Version Date / Number #1-1 -->
                                                                                                <!-- G.k.4.r.11.2b: Parent Route of Administration TermID #1-1 -->
                                                                                                <xsl:if test="Gk4r111">
                                                                                                    <xsl:choose>
                                                                                                        <xsl:when test="Gk4r111[@nf]">
                                                                                                            <originalTest nullFlavor="{Gk4r111/@nf}"/>
                                                                                                        </xsl:when>
                                                                                                        <xsl:otherwise>
                                                                                                            <originalText>
                                                                                                                <xsl:value-of select="Gk4r111"/>
                                                                                                            </originalText>
                                                                                                        </xsl:otherwise>
                                                                                                    </xsl:choose>
                                                                                                    <!-- G.k.4.r.11.1: Parent Route of Administration (free text) #1-1 -->
                                                                                                </xsl:if>
                                                                                            </xsl:element>
                                                                                        </observation>
                                                                                    </inboundRelationship>
                                                                                </xsl:if>
                                                                            </substanceAdministration>
                                                                        </outboundRelationship2>
                                                                    </xsl:for-each>
                                                                    <xsl:if test="Gk5a or Gk5b">
                                                                        <outboundRelationship2 typeCode="SUMM">
                                                                            <observation classCode="OBS" moodCode="EVN">
                                                                                <code code="14" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="cumulativeDoseToReaction"/>
                                                                                <value xsi:type="PQ" value="{Gk5a}" unit="{Gk5b}"/>
                                                                                <!-- G.k.5a: Cumulative Dose to First Reaction (number) #1 -->
                                                                                <!-- G.k.5b: Cumulative Dose to First Reaction (unit) #1 -->
                                                                            </observation>
                                                                        </outboundRelationship2>
                                                                    </xsl:if>
                                                                    <xsl:if test="Gk6a or Gk6b">
                                                                        <outboundRelationship2 typeCode="PERT">
                                                                            <observation classCode="OBS" moodCode="EVN">
                                                                                <code code="16" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="gestationPeriod"/>
                                                                                <value xsi:type="PQ" value="{Gk6a}" unit="{Gk56b}"/>
                                                                                <!-- G.k.6a: Gestation Period at Time of Exposure (number) #1 -->
                                                                                <!-- G.k.6b: Gestation Period at Time of Exposure (unit) #1 -->
                                                                            </observation>
                                                                        </outboundRelationship2>
                                                                    </xsl:if>
                                                                    <xsl:for-each select="Gk9i">
                                                                        <outboundRelationship2 typeCode="PERT">
                                                                            <observation classCode="OBS" moodCode="EVN">
                                                                                <code code="31" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="recurranceOfReaction"/>
                                                                                <value xsi:type="CE" code="{Gk9i4}" codeSystem="2.16.840.1.113883.3.989.2.1.1.16" codeSystemVersion="1.0"/>
                                                                                <!-- G.k.9.i.4: Did Reaction Recur on Re-administration? Drug #1, Reaction #1 -->
                                                                                <outboundRelationship1 typeCode="REFR">
                                                                                    <actReference classCode="OBS" moodCode="EVN">
                                                                                        <id root="154eb889-958b-45f2-a02f-42d4d6f4657f"/> <!--????????? -->
                                                                                    </actReference>
                                                                                </outboundRelationship1>
                                                                            </observation>
                                                                        </outboundRelationship2>
                                                                    </xsl:for-each>
                                                                    <xsl:for-each select="Gk10r">
                                                                        <outboundRelationship2 typeCode="REFR">
                                                                            <observation classCode="OBS" moodCode="EVN">
                                                                                <code code="9" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="codedDrugInformation"/>
                                                                                <value xsi:type="CE" code="{.}" codeSystem="2.16.840.1.113883.3.989.2.1.1.17" codeSystemVersion="1.0"/>
                                                                                <!-- G.k.10.r: Additional Information on Drug (coded)(repeat as necessary) #1-1 -->
                                                                            </observation>
                                                                        </outboundRelationship2>
                                                                    </xsl:for-each>
                                                                    <xsl:if test="Gk11">
                                                                        <outboundRelationship2 typeCode="REFR">
                                                                            <observation classCode="OBS" moodCode="EVN">
                                                                                <code code="2" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="additionalInformation"/>
                                                                                <value xsi:type="ST">
                                                                                    <xsl:value-of select="Gk11"/>
                                                                                </value>
                                                                                <!-- G.k.11: Additional Information on Drug (free text) #1 -->
                                                                            </observation>
                                                                        </outboundRelationship2>
                                                                    </xsl:if>
                                                                    <xsl:for-each select="Gk7r">
                                                                        <inboundRelationship typeCode="RSON">
                                                                            <observation classCode="OBS" moodCode="EVN">
                                                                                <code code="19" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="indication"/>
                                                                                <xsl:if test="Gk7r1 or Gk7r2a or Gk7r2b">
                                                                                    <xsl:element name="value">
                                                                                        <xsl:attribute name="xsi:type">CE</xsl:attribute>
                                                                                        <xsl:attribute name="codeSystem">2.16.840.1.113883.6.163</xsl:attribute>
                                                                                        <xsl:if test="Gk7r2a or Gk7r2b">
                                                                                            <xsl:attribute name="code">
                                                                                                <xsl:value-of select="Gk7r2b"/>
                                                                                            </xsl:attribute>
                                                                                            <xsl:attribute name="codeSystemVersion">
                                                                                                <xsl:value-of select="Gk7r2a"/>
                                                                                            </xsl:attribute>
                                                                                            <!-- G.k.7.r.2a: MedDRA Version for Indication #1-1 -->
                                                                                            <!-- G.k.7.r.2b: Indication (MedDRA code)  #1-1 -->
                                                                                        </xsl:if>
                                                                                        <xsl:if test="Gk7r1">
                                                                                            <xsl:choose>
                                                                                                <xsl:when test="Gk7r1[@nf]">
                                                                                                    <originalTest nullFlavor="{Gk7r1/@nf}"/>
                                                                                                </xsl:when>
                                                                                                <xsl:otherwise>
                                                                                                    <originalText>
                                                                                                        <xsl:value-of select="Gk7r1"/>
                                                                                                    </originalText>
                                                                                                </xsl:otherwise>
                                                                                            </xsl:choose>
                                                                                            <!-- G.k.7.r.1: Indication as Reported by the Primary Source #1-1 -->
                                                                                        </xsl:if>
                                                                                    </xsl:element>
                                                                                </xsl:if>
                                                                                <performer typeCode="PRF">
                                                                                    <assignedEntity classCode="ASSIGNED">
                                                                                        <code code="3" codeSystem="2.16.840.1.113883.3.989.2.1.1.21" codeSystemVersion="1.0" displayName="sourceReporter"/>
                                                                                    </assignedEntity>
                                                                                </performer>
                                                                            </observation>
                                                                        </inboundRelationship>
                                                                    </xsl:for-each>
                                                                    <xsl:if test="Gk8">
                                                                        <inboundRelationship typeCode="CAUS">
                                                                            <act classCode="ACT" moodCode="EVN">
                                                                                <code code="{Gk8}" codeSystem="2.16.840.1.113883.3.989.2.1.1.15" codeSystemVersion="1.0"/>
                                                                                <!-- G.k.8: Action(s) Taken with Drug #1 -->
                                                                            </act>
                                                                        </inboundRelationship>
                                                                    </xsl:if>
                                                                </substanceAdministration>
                                                            </component>
                                                        </xsl:for-each>
                                                    </organizer>
                                                </subjectOf2>
                                            </primaryRole>
                                        </subject1>
                                        <xsl:for-each select="Gk">
                                            <component typeCode="COMP">
                                                <causalityAssessment classCode="OBS" moodCode="EVN">
                                                    <code code="20" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="interventionCharacterization"/>
                                                    <value xsi:type="CE" code="{Gk1}" codeSystem="2.16.840.1.113883.3.989.2.1.1.13" codeSystemVersion="1.0"/>
                                                    <!-- G.k.1: Characterisation of Drug Role Drug #1 -->
                                                    <subject2 typeCode="SUBJ">
                                                        <productUseReference classCode="SBADM" moodCode="EVN">
                                                            <id root="3c91b4d5-e039-4a7a-9c30-67671b0ef9e4"/> <!-- ????????? -->
                                                        </productUseReference>
                                                    </subject2>
                                                </causalityAssessment>
                                            </component>
                                        </xsl:for-each>
                                        <xsl:for-each select="Gk/Gk9i">
                                            <xsl:for-each select="Gk9i2r">
                                                <component typeCode="COMP">
                                                    <causalityAssessment classCode="OBS" moodCode="EVN">
                                                        <code code="39" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="causality"/>
                                                        <xsl:if test="Gk9i2r3">
                                                            <value xsi:type="ST">
                                                                <xsl:value-of select="Gk9i2r3"/>
                                                            </value>
                                                            <!-- G.k.9.i.2.r.3: Result of Assessment Drug #1, Reaction #1, Assessment #1 -->
                                                        </xsl:if>
                                                        <xsl:if test="Gk9i2r2">
                                                            <methodCode>
                                                                <originalText>
                                                                    <xsl:value-of select="Gk9i2r2"/>
                                                                </originalText>
                                                                <!-- G.k.9.i.2.r.2: Method of Assessment Drug #1, Reaction #1, Assessment #1 -->
                                                            </methodCode>
                                                        </xsl:if>
                                                        <xsl:if test="Gk9i2r1">
                                                            <author typeCode="AUT">
                                                                <assignedEntity classCode="ASSIGNED">
                                                                    <code>
                                                                        <originalText>
                                                                            <xsl:value-of select="Gk9i2r1"/>
                                                                        </originalText>
                                                                        <!-- G.k.9.i.2.r.1: Source of Assessment Drug #1, Reaction #1, Assessment #1 -->
                                                                    </code>
                                                                </assignedEntity>
                                                            </author>
                                                        </xsl:if>
                                                        <subject1 typeCode="SUBJ">
                                                            <adverseEffectReference classCode="OBS" moodCode="EVN">
                                                                <id root="154eb889-958b-45f2-a02f-42d4d6f4657f"/> <!-- ?????? -->
                                                            </adverseEffectReference>
                                                        </subject1>
                                                        <subject2 typeCode="SUBJ">
                                                            <productUseReference classCode="SBADM" moodCode="EVN">
                                                                <id root="3c91b4d5-e039-4a7a-9c30-67671b0ef9e4"/> <!-- ?????? -->
                                                            </productUseReference>
                                                        </subject2>
                                                    </causalityAssessment>
                                                </component>
                                            </xsl:for-each>
                                        </xsl:for-each>
                                        <xsl:if test="H2">
                                            <component1 typeCode="COMP">
                                                <observationEvent classCode="OBS" moodCode="EVN">
                                                    <code code="10" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="comment"/>
                                                    <value xsi:type="ED">
                                                        <xsl:value-of select="H2"/>
                                                    </value>
                                                    <!-- H.2: Reporter's Comments -->
                                                    <author typeCode="AUT">
                                                        <assignedEntity classCode="ASSIGNED">
                                                            <code code="3" codeSystem="2.16.840.1.113883.3.989.2.1.1.21" codeSystemVersion="1.0" displayName="sourceReporter"/>
                                                        </assignedEntity>
                                                    </author>
                                                </observationEvent>
                                            </component1>
                                        </xsl:if>
                                        <xsl:for-each select="H3r">
                                            <component1 typeCode="COMP">
                                                <observationEvent classCode="OBS" moodCode="EVN">
                                                    <code code="15" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="diagnosis"/>
                                                    <value xsi:type="CE" code="{H3r1b}" codeSystem="2.16.840.1.113883.6.163" codeSystemVersion="{H3r1a}"/>
                                                    <!-- H.3.r.1a: MedDRA Version for Sender's Diagnosis / Syndrome and / or Reclassification of Reaction / Event #1 -->
                                                    <!-- H.3.r.1b: Sender's Diagnosis / Syndrome and / or Reclassification of Reaction / Event  (MedDRA code) #1 -->
                                                    <author typeCode="AUT">
                                                        <assignedEntity classCode="ASSIGNED">
                                                            <code code="1" codeSystem="2.16.840.1.113883.3.989.2.1.1.21" codeSystemVersion="1.0" displayName="sender"/>
                                                        </assignedEntity>
                                                    </author>
                                                </observationEvent>
                                            </component1>
                                        </xsl:for-each>
                                        <xsl:if test="H4">
                                            <component1 typeCode="COMP">
                                                <observationEvent classCode="OBS" moodCode="EVN">
                                                    <code code="10" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="comment"/>
                                                    <value xsi:type="ED">
                                                        <xsl:value-of select="H4"/>
                                                    </value>
                                                    <!-- H.4: Sender's Comments -->
                                                    <author typeCode="AUT">
                                                        <assignedEntity classCode="ASSIGNED">
                                                            <code code="1" codeSystem="2.16.840.1.113883.3.989.2.1.1.21" codeSystemVersion="1.0" displayName="sender"/>
                                                        </assignedEntity>
                                                    </author>
                                                </observationEvent>
                                            </component1>
                                        </xsl:if>
                                    </adverseEventAssessment>
                                </component>
                                <xsl:if test="C161">
                                    <component typeCode="COMP">
                                        <observationEvent classCode="OBS" moodCode="EVN">
                                            <code code="1" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="additionalDocumentsAvailable"/>
                                            <value xsi:type="BL" value="{C161}"/>
                                            <!-- C.1.6.1: Are Additional Documents Available? -->
                                        </observationEvent>
                                    </component>
                                </xsl:if>
                                <xsl:if test="C17">
                                    <component typeCode="COMP">
                                        <observationEvent classCode="OBS" moodCode="EVN">
                                            <code code="23" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="localCriteriaForExpedited"/>
                                            <xsl:choose>
                                                <xsl:when test="C17[@nf]">
                                                    <value xsi:type="BL" nullFlavor="{C17/@nf}"/>
                                                </xsl:when>
                                                <xsl:otherwise>
                                                    <value xsi:type="BL" value="{C17}"/>
                                                </xsl:otherwise>
                                            </xsl:choose>
                                            <!-- C.1.7: Does This Case Fulfil the Local Criteria for an Expedited Report? -->
                                        </observationEvent>
                                    </component>
                                </xsl:if>
                                <xsl:for-each select="H5r">
                                    <component typeCode="COMP">
                                        <observationEvent classCode="OBS" moodCode="EVN">
                                            <code code="36" codeSystem="2.16.840.1.113883.3.989.2.1.1.19" codeSystemVersion="1.1" displayName="summaryAndComment"/>
                                            <value xsi:type="ED" language="{H5r1b}">
                                                <xsl:value-of select="H5r1a"/>
                                            </value>
                                            <!-- H.5.r.1a: Case Summary and Reporter's Comments Text  #2 -->
                                            <!-- H.5.r.1b: Case Summary and Reporter's Comments Language #2 -->
                                            <author typeCode="AUT">
                                                <assignedEntity classCode="ASSIGNED">
                                                    <code code="2" codeSystem="2.16.840.1.113883.3.989.2.1.1.21" codeSystemVersion="1.0" displayName="reporter"/>
                                                </assignedEntity>
                                            </author>
                                        </observationEvent>
                                    </component>
                                </xsl:for-each>
                                <xsl:if test="C182">
                                    <outboundRelationship typeCode="SPRT">
                                        <relatedInvestigation classCode="INVSTG" moodCode="EVN">
                                            <code code="1" codeSystem="2.16.840.1.113883.3.989.2.1.1.22" codeSystemVersion="1.0" displayName="initialReport"/>
                                            <xsl:if test="C182">
                                                <subjectOf2 typeCode="SUBJ">
                                                    <controlActEvent classCode="CACT" moodCode="EVN">
                                                        <author typeCode="AUT">
                                                            <assignedEntity classCode="ASSIGNED">
                                                                <code code="{C182}" codeSystem="2.16.840.1.113883.3.989.2.1.1.3" codeSystemVersion="1.0"/>
                                                                <!-- C.1.8.2: First Sender of This Case -->
                                                            </assignedEntity>
                                                        </author>
                                                    </controlActEvent>
                                                </subjectOf2>
                                            </xsl:if>
                                        </relatedInvestigation>
                                    </outboundRelationship>
                                </xsl:if>
                                <xsl:for-each select="C110r">
                                    <outboundRelationship typeCode="SPRT">
                                        <relatedInvestigation classCode="INVSTG" moodCode="EVN">
                                            <code nullFlavor="NA"/>
                                            <xsl:if test="C110r">
                                                <subjectOf2 typeCode="SUBJ">
                                                    <controlActEvent classCode="CACT" moodCode="EVN">
                                                        <id extension="{.}" root="2.16.840.1.113883.3.989.2.1.3.2"/>
                                                        <!-- C.1.10.r: Identification Number of the Report Which Is Linked to This Report (repeat as necessary)  #1 -->
                                                    </controlActEvent>
                                                </subjectOf2>
                                            </xsl:if>
                                        </relatedInvestigation>
                                    </outboundRelationship>
                                </xsl:for-each>
                                <xsl:for-each select="C2r">
                                    <outboundRelationship typeCode="SPRT">
                                        <xsl:if test="C2r5">
                                            <priorityNumber value="{C2r5}"/>
                                            <!-- C.2.r.5: Primary Source for Regulatory Purposes #1 -->
                                        </xsl:if>
                                        <relatedInvestigation classCode="INVSTG" moodCode="EVN">
                                            <code code="2" codeSystem="2.16.840.1.113883.3.989.2.1.1.22" codeSystemVersion="1.0" displayName="sourceReport"/>
                                            <subjectOf2 typeCode="SUBJ">
                                                <controlActEvent classCode="CACT" moodCode="EVN">
                                                    <author typeCode="AUT">
                                                        <assignedEntity classCode="ASSIGNED">
                                                            <xsl:if test="C2r23 or C2r24 or C2r25 or C2r26">
                                                                <addr>
                                                                    <xsl:if test="C2r23">
                                                                        <xsl:choose>
                                                                            <xsl:when test="C2r23[@nf]">
                                                                                <streetAddressLine nullFlavor="{C2r23/@nf}"/>
                                                                            </xsl:when>
                                                                            <xsl:otherwise>
                                                                                <streetAddressLine>
                                                                                    <xsl:value-of select="C2r23"/>
                                                                                </streetAddressLine>
                                                                            </xsl:otherwise>
                                                                        </xsl:choose>
                                                                        <!-- C.2.r.2.3: Reporter's Street #1 -->
                                                                    </xsl:if>
                                                                    <xsl:if test="C2r24">
                                                                        <xsl:choose>
                                                                            <xsl:when test="C2r24[@nf]">
                                                                                <city nullFlavor="{C2r24/@nf}"/>
                                                                            </xsl:when>
                                                                            <xsl:otherwise>
                                                                                <city>
                                                                                    <xsl:value-of select="C2r24"/>
                                                                                </city>
                                                                            </xsl:otherwise>
                                                                        </xsl:choose>
                                                                        <!-- C.2.r.2.4: Reporter's City #1 -->
                                                                    </xsl:if>
                                                                    <xsl:if test="C2r25">
                                                                        <xsl:choose>
                                                                            <xsl:when test="C2r25[@nf]">
                                                                                <state nullFlavor="{C2r25/@nf}"/>
                                                                            </xsl:when>
                                                                            <xsl:otherwise>
                                                                                <state>
                                                                                    <xsl:value-of select="C2r25"/>
                                                                                </state>
                                                                            </xsl:otherwise>
                                                                        </xsl:choose>
                                                                        <!-- C.2.r.2.5: Reporter's State or Province #1 -->
                                                                    </xsl:if>
                                                                    <xsl:if test="C2r26">
                                                                        <xsl:choose>
                                                                            <xsl:when test="C2r26[@nf]">
                                                                                <postalCode nullFlavor="{C2r26/@nf}"/>
                                                                            </xsl:when>
                                                                            <xsl:otherwise>
                                                                                <postalCode>
                                                                                    <xsl:value-of select="C2r26"/>
                                                                                </postalCode>
                                                                            </xsl:otherwise>
                                                                        </xsl:choose>
                                                                        <!-- C.2.r.2.6: Reporter's Postcode #1 -->
                                                                    </xsl:if>
                                                                </addr>
                                                            </xsl:if>
                                                            <xsl:if test="C2r27">
                                                                <telecom value="{concat('tel:', C2r27)}"/>
                                                                <!-- C.2.r.2.7: Reporter's Telephone #1 -->
                                                                <xsl:choose>
                                                                    <xsl:when test="C2r27[@nf]">
                                                                        <telecom value="tel:" nullFlavor="C2r27/@nf"/>
                                                                    </xsl:when>
                                                                    <xsl:otherwise>
                                                                        <telecom value="{concat('tel:', C2r27)}"/>
                                                                    </xsl:otherwise>
                                                                </xsl:choose>
                                                            </xsl:if>
                                                            <xsl:if test="C2r11 or C2r12 or C2r13 or C2r14 or C2r3 or C2r4">
                                                                <assignedPerson classCode="PSN" determinerCode="INSTANCE">
                                                                    <xsl:if test="C2r11 or C2r12 or C2r13 or C2r14">
                                                                        <name>
                                                                            <xsl:if test="C2r11">
                                                                                <xsl:choose>
                                                                                    <xsl:when test="C2r11[@nf]">
                                                                                        <prefix nullFlavor="{C2r11/@nf}"/>
                                                                                    </xsl:when>
                                                                                    <xsl:otherwise>
                                                                                        <prefix>
                                                                                            <xsl:value-of select="C2r11"/>
                                                                                        </prefix>
                                                                                    </xsl:otherwise>
                                                                                </xsl:choose>
                                                                                <!-- C.2.r.1.1: Reporter's Title #1 -->
                                                                            </xsl:if>
                                                                            <xsl:if test="C2r12">
                                                                                <xsl:choose>
                                                                                    <xsl:when test="C2r12[@nf]">
                                                                                        <given nullFlavor="{C2r12/@nf}"/>
                                                                                    </xsl:when>
                                                                                    <xsl:otherwise>
                                                                                        <given>
                                                                                            <xsl:value-of select="C2r12"/>
                                                                                        </given>
                                                                                    </xsl:otherwise>
                                                                                </xsl:choose>
                                                                                <!-- C.2.r.1.2: Reporter's Given Name #1 -->
                                                                            </xsl:if>
                                                                            <xsl:if test="C2r13">
                                                                                <xsl:choose>
                                                                                    <xsl:when test="C2r13[@nf]">
                                                                                        <given nullFlavor="{C2r13/@nf}"/>
                                                                                    </xsl:when>
                                                                                    <xsl:otherwise>
                                                                                        <given>
                                                                                            <xsl:value-of select="C2r13"/>
                                                                                        </given>
                                                                                    </xsl:otherwise>
                                                                                </xsl:choose>
                                                                                <!-- C.2.r.1.3: Reporter's Middle Name #1 -->
                                                                            </xsl:if>
                                                                            <xsl:if test="C2r14">
                                                                                <xsl:choose>
                                                                                    <xsl:when test="C2r14[@nf]">
                                                                                        <family nullFlavor="{C2r14/@nf}"/>
                                                                                    </xsl:when>
                                                                                    <xsl:otherwise>
                                                                                        <family>
                                                                                            <xsl:value-of select="C2r14"/>
                                                                                        </family>
                                                                                    </xsl:otherwise>
                                                                                </xsl:choose>
                                                                                <!-- C.2.r.1.4: Reporter's Family Name #1 -->
                                                                            </xsl:if>
                                                                        </name>
                                                                    </xsl:if>
                                                                    <xsl:if test="C2r4">
                                                                        <asQualifiedEntity classCode="QUAL">   
                                                                            <xsl:choose>
                                                                                <xsl:when test="C2r4[@nf]">
                                                                                    <code nullFlavor="{C2r4/@nf}" codeSystem="2.16.840.1.113883.3.989.2.1.1.6" codeSystemVersion="1.0"/>
                                                                                </xsl:when>
                                                                                <xsl:otherwise>
                                                                                    <code code="{C2r4}" codeSystem="2.16.840.1.113883.3.989.2.1.1.6" codeSystemVersion="1.0"/>
                                                                                </xsl:otherwise>
                                                                            </xsl:choose>
                                                                            <!-- C.2.r.4: Qualification #1 -->
                                                                        </asQualifiedEntity>
                                                                    </xsl:if>
                                                                    <xsl:if test="C2r3">
                                                                        <asLocatedEntity classCode="LOCE">
                                                                            <location classCode="COUNTRY" determinerCode="INSTANCE">
                                                                                <xsl:choose>
                                                                                    <xsl:when test="C2r3[@nf]">
                                                                                        <code nullFlavor="{C2r3/@nf}" codeSystem="1.0.3166.1.2.2"/>
                                                                                    </xsl:when>
                                                                                    <xsl:otherwise>
                                                                                        <code code="{C2r3}" codeSystem="1.0.3166.1.2.2"/>
                                                                                    </xsl:otherwise>
                                                                                </xsl:choose>
                                                                                <!-- C.2.r.3: Reporter's Country Code #1 -->
                                                                            </location>
                                                                        </asLocatedEntity>
                                                                    </xsl:if>
                                                                </assignedPerson>
                                                            </xsl:if>
                                                            <xsl:if test="C2r21 or C2r22">
                                                                <representedOrganization classCode="ORG" determinerCode="INSTANCE">
                                                                    <xsl:if test="C2r22">
                                                                        <xsl:choose>
                                                                            <xsl:when test="C2r22[@nf]">
                                                                                <name nullFlavor="{C2r22/@nf}"/>
                                                                            </xsl:when>
                                                                            <xsl:otherwise>
                                                                                <name>
                                                                                    <xsl:value-of select="C2r22"/>
                                                                                </name>
                                                                            </xsl:otherwise>
                                                                        </xsl:choose>
                                                                        <!-- C.2.r.2.2: Reporter's Department #1 -->
                                                                    </xsl:if>
                                                                    <xsl:if test="C2r21">
                                                                        <assignedEntity classCode="ASSIGNED">
                                                                            <representedOrganization classCode="ORG" determinerCode="INSTANCE">
                                                                                <xsl:choose>
                                                                                    <xsl:when test="C2r21[@nf]">
                                                                                        <name nullFlavor="{C2r21/@nf}"/>
                                                                                    </xsl:when>
                                                                                    <xsl:otherwise>
                                                                                        <name>
                                                                                            <xsl:value-of select="C2r21"/>
                                                                                        </name>
                                                                                    </xsl:otherwise>
                                                                                </xsl:choose>
                                                                                <!-- C.2.r.2.1: Reporter's Organisation #1 -->
                                                                            </representedOrganization>
                                                                        </assignedEntity>
                                                                    </xsl:if>
                                                                </representedOrganization>
                                                            </xsl:if>
                                                        </assignedEntity>
                                                    </author>
                                                </controlActEvent>
                                            </subjectOf2>
                                        </relatedInvestigation>
                                    </outboundRelationship>
                                </xsl:for-each>
                                <subjectOf1 typeCode="SUBJ">
                                    <controlActEvent classCode="CACT" moodCode="EVN">
                                        <author typeCode="AUT">
                                            <assignedEntity classCode="ASSIGNED">
                                                <code code="{C31}" codeSystem="2.16.840.1.113883.3.989.2.1.1.7" codeSystemVersion="1.0"/>
                                                <!-- C.3.1: Sender Type -->
                                                <xsl:if test="C341 or C342 or C343 or C344">
                                                    <addr>
                                                        <xsl:if test="C341">
                                                            <streetAddressLine>
                                                                <xsl:value-of select="C341"/>
                                                            </streetAddressLine>
                                                            <!-- C.3.4.1: Sender's Street Address -->
                                                        </xsl:if>
                                                        <xsl:if test="C342">
                                                            <city>
                                                                <xsl:value-of select="C342"/>
                                                            </city>
                                                            <!-- C.3.4.2: Sender's City -->
                                                        </xsl:if>
                                                        <xsl:if test="C343">
                                                            <state>
                                                                <xsl:value-of select="C343"/>
                                                            </state>
                                                            <!-- C.3.4.3: Sender's State or Province -->
                                                        </xsl:if>
                                                        <xsl:if test="C344">
                                                            <postalCode>
                                                                <xsl:value-of select="C344"/>
                                                            </postalCode>
                                                            <!-- C.3.4.4: Sender's Postcode -->
                                                        </xsl:if>
                                                    </addr>
                                                </xsl:if>
                                                <xsl:if test="C346">
                                                    <telecom value="{concat('tel:', C346)}"/>
                                                    <!-- C.3.4.6: Sender's Telephone -->
                                                </xsl:if>
                                                <xsl:if test="C347">
                                                    <telecom value="{concat('fax:', C347)}"/>
                                                    <!-- C.3.4.7: Sender's Fax -->
                                                </xsl:if>
                                                <xsl:if test="C348">
                                                    <telecom value="{concat('mailto:', C348)}"/>
                                                    <!-- C.3.4.8: Sender's E-mail Address -->
                                                </xsl:if>
                                                <xsl:if test="C332 or C333 or C334 or C335 or C345">
                                                    <assignedPerson classCode="PSN" determinerCode="INSTANCE">
                                                        <xsl:if test="C332 or C333 or C334 or C335">
                                                            <name>
                                                                <xsl:if test="C332">
                                                                    <prefix>
                                                                        <xsl:value-of select="C332"/>
                                                                    </prefix>
                                                                    <!-- C.3.3.2: Sender's Title -->
                                                                </xsl:if>
                                                                <xsl:if test="C333">
                                                                    <given>
                                                                        <xsl:value-of select="C333"/>
                                                                    </given>
                                                                    <!-- C.3.3.3: Sender's Given Name -->
                                                                </xsl:if>
                                                                <xsl:if test="C334">
                                                                    <given>
                                                                        <xsl:value-of select="C334"/>
                                                                    </given>
                                                                    <!-- C.3.3.4: Sender's Middle Name -->
                                                                </xsl:if>
                                                                <xsl:if test="C335">
                                                                    <family>
                                                                        <xsl:value-of select="C335"/>
                                                                    </family>
                                                                    <!-- C.3.3.5: Sender's Family Name -->
                                                                </xsl:if>
                                                            </name>
                                                        </xsl:if>
                                                        <xsl:if test="C345">
                                                            <asLocatedEntity classCode="LOCE">
                                                                <location classCode="COUNTRY" determinerCode="INSTANCE">
                                                                    <code code="{C345}" codeSystem="1.0.3166.1.2.2"/>
                                                                    <!-- C.3.4.5: Sender's Country Code -->
                                                                </location>
                                                            </asLocatedEntity>
                                                        </xsl:if>
                                                    </assignedPerson>
                                                </xsl:if>
                                                <xsl:if test="C32 or C331">
                                                    <representedOrganization classCode="ORG" determinerCode="INSTANCE">
                                                        <xsl:if test="C331">
                                                            <name>
                                                                <xsl:value-of select="C331"/>
                                                            </name>
                                                            <!-- C.3.3.1: Sender's Department -->
                                                        </xsl:if>
                                                        <xsl:if test="C32">
                                                            <assignedEntity classCode="ASSIGNED">
                                                                <representedOrganization classCode="ORG" determinerCode="INSTANCE">
                                                                    <name>
                                                                        <xsl:value-of select="C32"/>
                                                                    </name>
                                                                    <!-- C.3.2: Sender's Organisation -->
                                                                </representedOrganization>
                                                            </assignedEntity>
                                                        </xsl:if>
                                                    </representedOrganization>
                                                </xsl:if>
                                            </assignedEntity>
                                        </author>
                                    </controlActEvent>
                                </subjectOf1>
                                <xsl:for-each select="C191r">
                                    <subjectOf1 typeCode="SUBJ">
                                        <controlActEvent classCode="CACT" moodCode="EVN">
                                            <id assigningAuthorityName="{C191r1}" extension="{C191r2}" root="2.16.840.1.113883.3.989.2.1.3.3"/>
                                            <!-- C.1.9.1.r.1: Source(s) of the Case Identifier (repeat as necessary) #1 -->
                                            <!-- C.1.9.1.r.2 Case Identifier(s) #1 -->
                                        </controlActEvent>
                                    </subjectOf1>
                                </xsl:for-each>
                                <subjectOf2 typeCode="SUBJ">
                                    <investigationCharacteristic classCode="OBS" moodCode="EVN">
                                        <code code="1" codeSystem="2.16.840.1.113883.3.989.2.1.1.23" codeSystemVersion="1.0" displayName="ichReportType"/>
                                        <value xsi:type="CE" code="{C13}" codeSystem="2.16.840.1.113883.3.989.2.1.1.2" codeSystemVersion="1.0"/>
                                        <!-- C.1.3 Type of Report -->
                                    </investigationCharacteristic>
                                </subjectOf2>
                                <subjectOf2 typeCode="SUBJ">
                                    <investigationCharacteristic classCode="OBS" moodCode="EVN">
                                        <code code="2" codeSystem="2.16.840.1.113883.3.989.2.1.1.23" codeSystemVersion="1.0" displayName="otherCaseIds"/>
                                        <xsl:choose>
                                            <xsl:when test="C191[@nf]">
                                                <value xsi:type="BL" nullFlavor="{C191/@nf}"/>
                                            </xsl:when>
                                            <xsl:otherwise>
                                                <value xsi:type="BL" value="{C191}"/>
                                            </xsl:otherwise>
                                        </xsl:choose>
                                        <!-- C.1.9.1 Other Case Identifiers in Previous Transmissions -->
                                    </investigationCharacteristic>
                                </subjectOf2>
                                <xsl:if test="C1111">
                                    <subjectOf2 typeCode="SUBJ">
                                        <investigationCharacteristic classCode="OBS" moodCode="EVN">
                                            <code code="3" codeSystem="2.16.840.1.113883.3.989.2.1.1.23" codeSystemVersion="1.0" displayName="nullificationAmendmentCode"/>
                                            <value xsi:type="CE" code="{C1111}" codeSystem="2.16.840.1.113883.3.989.2.1.1.5" codeSystemVersion="1.0"/>
                                            <!-- C.1.11.1: Report Nullification / Amendment -->
                                        </investigationCharacteristic>
                                    </subjectOf2>
                                </xsl:if> 
                                <xsl:if test="C1112">
                                    <subjectOf2 typeCode="SUBJ">
                                        <investigationCharacteristic classCode="OBS" moodCode="EVN">
                                            <code code="4" codeSystem="2.16.840.1.113883.3.989.2.1.1.23" codeSystemVersion="1.0" displayName="nullificationAmendmentReason"/>
                                            <value xsi:type="CE">
                                                <originalText>
                                                    <xsl:value-of select="C1112"/>
                                                </originalText>
                                                <!-- C.1.11.2: Reason for Nullification / Amendment -->
                                            </value>
                                        </investigationCharacteristic>
                                    </subjectOf2>
                                </xsl:if>
                            </investigationEvent>
                        </subject>
                    </controlActProcess>
                </PORR_IN049016UV>
            </xsl:for-each>
            <receiver typeCode="RCV">
                <device classCode="DEV" determinerCode="INSTANCE">
                    <id extension="{report/N14}" root="2.16.840.1.113883.3.989.2.1.3.14"/>
                    <!-- N.1.4: Batch Receiver Identifier -->
                </device>
            </receiver>
            <sender typeCode="SND">
                <device classCode="DEV" determinerCode="INSTANCE">
                    <id extension="{report/N13}" root="2.16.840.1.113883.3.989.2.1.3.13"/>
                    <!-- N.1.3: Batch Sender Identifier -->
                </device>
            </sender>
        </MCCI_IN200100UV01>
    </xsl:template>
</xsl:stylesheet>
