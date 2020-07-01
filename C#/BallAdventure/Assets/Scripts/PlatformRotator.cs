using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlatformRotator : MonoBehaviour {

    public float speed = 1.0f;
    public float maxRotation = 30.0f;

	void Update ()
    {
        transform.rotation = Quaternion.Euler(0.0f, 0.0f, maxRotation * Mathf.Sin(Time.time * speed));
	}
}
