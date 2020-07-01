using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GroundMoving : MonoBehaviour {

    public Transform farEnd;
    private Vector3 pointA;
    private Vector3 pointB;
    public float secondsForOneLength;
    
	// Use this for initialization
	void Start ()
    {
        pointA = transform.position;
        pointB = farEnd.position;
	}
	
	// Update is called once per frame
	void Update ()
    {
        transform.position = Vector3.Lerp(pointA, pointB, Mathf.SmoothStep(0f, 1f, Mathf.PingPong(Time.time / secondsForOneLength, 1f)));
	}
}
