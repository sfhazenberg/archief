using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Floor1Door : MonoBehaviour {

    public Transform farEnd;
    public float speed = 3;

    private Vector3 origin;
    private bool move = false;

	void Start() {
        origin = transform.position;
	}
	
	void Update() {
        float step = speed * Time.deltaTime;

		if(move) {
            transform.position = Vector3.MoveTowards(transform.position, farEnd.position, step);
        }
        else {
            transform.position = Vector3.MoveTowards(transform.position, origin, step);
        }
	}

    public void LowerDoor() {
        move = true;
    }

    public void RaiseDoor() {
        move = false;
    }
}
