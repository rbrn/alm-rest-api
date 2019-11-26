package org.alm.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Entities")
public class RunSteps extends Entities<RunStep>
{
    public RunSteps() {

    }
    public RunSteps(Entities entities)
    {
       super(entities.entities());
    }
}
