using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class LightController : MonoBehaviour {

    public bool lightRotate = false;    //zet draaien van lichtbron aan/uit
    public bool lightFlick = false;     //zet knipperen van lichtbron aan/uit

    private Light myLight;

    public float timeOn = 0.1f;         //stelt hoe lang lichtbron aanblijft
    public float timeOff = 0.5f;        //stelt hoe lang lichtbron uitblijft
    private float changeTime = 0;

    public float rotatorSpeed = 10f;    //stelt de snelheid van de rotatie

    // Use this for initialization
	void Start () {
        myLight = GetComponent<Light>();
    }

    // Update is called once per frame
    void Update()
    {
        if(lightFlick == true)
        {
            lightFlicker();
        }   

        if (lightRotate == true)
        {
            lightRotater();
        }
    }

    private void lightFlicker()
    {
        if (Time.time > changeTime)
        {
            myLight.enabled = !myLight.enabled;
            if (myLight.enabled)
            {
                changeTime = Time.time + timeOn;
            }
            else
            {
                changeTime = Time.time + timeOff;
            }
        }
    }

    private void lightRotater()
    {
        transform.Rotate(Vector3.up, rotatorSpeed * Time.deltaTime);
    }
}
